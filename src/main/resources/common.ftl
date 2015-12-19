<#--
Author: Johnson
Email: 169565@qq.com
The macros are capable of general CRUDs as well as table join queries
-->


<#include 'columns.ftl'>

<#--
table: if table parameter is missed, the 'tableName' variable in mapper will be used.
alias: column names are binded to the table alias if provided. e.g. 'u.name AS u_name'.
-->
<#macro fields table='' alias=''>
    <#local _table = table>
    <#if !table?has_content || !tableColumns[table]??>
        <#local _table = tableName>
    </#if>
        
    <#local _columns = baseColumns + tableColumns[_table]>
    
    <#list _columns?keys as column>
        <#if alias?has_content>
            ${alias}.${column} AS ${alias}_${column}
        <#else>
            ${column}
        </#if>
        <#if column_has_next>,</#if>
    </#list>
</#macro>

<#--
#{name}
#{criteria.name}
#{criteria.u_name}
-->
<#macro param column container='' table='' alias=''>
    <#local _table = tableName>
    <#if table?has_content && tableColumns[table]??>
        <#local _table = table>
    </#if>
    <#local _columns = baseColumns+tableColumns[_table]>
    <#local _column = column>
    <#if alias?has_content>
        <#local _column = alias+'_'+column>
    </#if>
    
    <#if container?has_content>
        <#if .data_model[container][_column]??>
            ${r"#{"}${container}.${_column}
        <#else>
            ${r"#{"}${container}.${column}
        </#if>
    <#else>
        ${r"#{"}${column}
    </#if>
    <#if _columns[column]??>
        <#if _columns[column]['javaType']??>
            ,javaType=${_columns[column]['javaType']},jdbcType=${_columns[column]['jdbcType']}
        <#elseif _columns[column]['typeHandler']??>
            ,typeHandler=${_columns[column]['typeHandler']}
        </#if>
    </#if>
    ${r"}"}
</#macro>

<#macro insert>
    INSERT INTO ${tableName} (
        <@fields />
    ) VALUES (
        <#list baseColumns?keys as column>
            <#if column == 'insertTime'>
                CURRENT_DATE
            <#else>
                <@param column />
            </#if>
            ,
        </#list>
        <#list tableColumns[tableName]?keys as column>
            <@param column /><#if column_has_next>,</#if>
        </#list>
    )
</#macro>

<#macro update>
    UPDATE ${tableName} SET
        updateTime = CURRENT_DATE,
        updateBy = <@param 'updateBy' />
        <#list tableColumns[tableName]?keys as column>
            <#if .data_model[column]??>
                , ${column} = <@param column />
            </#if>
        </#list>
    WHERE id = <@param 'id' />
</#macro>

<#macro delete>
    DELETE FROM ${tableName} WHERE id = <@param 'id' />
</#macro>

<#macro get>
    SELECT <@fields /> FROM ${tableName} WHERE id = <@p name='id' />
</#macro>

<#macro where table='' alias=''>
    <#if criteria?has_content>
        <#local _table = table>
        <#if !table?has_content || !tableColumns[table]??>
            <#local _table = tableName>
        </#if>
        <#local _columns = baseColumns+tableColumns[_table]>
        
        <#-- only applies to MAIN table -->
        <#if criteria['ids']?has_content && _table == tableName>
            AND <#if alias?has_content>${alias}.</#if>id IN (
                <#list criteria['ids'] as tid>
                    ${tid}<#if tid_has_next>,</#if>
                </#list>
            )
        </#if>
        <#list _columns?keys as column>
            <#if criteria[column]?? || alias?has_content && criteria[alias+'_'+column]??>
                AND <#if alias?has_content>${alias}.</#if>${column} = <@param column 'criteria' _table alias />
            </#if>
        </#list>
    </#if>
</#macro>

<#macro orderby>
    <#if regulation?? && regulation['sorts']?has_content>
        ORDER BY 
        <#list regulation['sorts'] as s>
            ${s['column']} ${s['order']}
            <#if s_has_next>,</#if>
        </#list>
    </#if>
</#macro>

<#macro singleSelect>
    <#if regulation?? && regulation['pagination']??>
        SELECT * FROM (
            SELECT T_.*, ROWNUM R_ FROM (
                SELECT <@fields /> FROM ${tableName} WHERE 1 = 1 <@where /> <@orderby />
            ) T_
            WHERE ROWNUM <= ${regulation['pagination']['offsetEnd']}
        )
        WHERE R_ > ${regulation['pagination']['offsetStart']}
    <#else>
        SELECT <@fields /> FROM ${tableName} WHERE 1 = 1 <@where /> <@orderby />
    </#if>
</#macro>

<#macro count>
    SELECT COUNT(*) FROM ${tableName} WHERE 1 = 1 <@where />
</#macro>

<#macro select>
    <@singleSelect />
</#macro>

<#--
mainTable: {name: 'Users', alias: 'u'}
links: [
                {name: 'Address', alias: 'a', link: 'u.addressId=a.id'},
                {name: 'Phone', alias: 'p', link: 'a.phoneId=p.id'}
              ]
-->
<#macro outerJoinCount mainTable links >
    SELECT COUNT(*) FROM ${mainTable['name']} ${mainTable['alias']}
    <#list links?keys as t>
        LEFT OUTER JOIN ${t} ${links[t]['alias']} ON ${links[t]['link']}
    </#list>
    WHERE 1 = 1
    <@where mainTable['name'] mainTable['alias'] />
    <#list links?keys as t>
        <@where t links[t]['alias'] />
    </#list>
</#macro>

<#macro outerJoinSelect mainTable links >
    <#if regulation?? && regulation['pagination']??>
        SELECT * FROM (
            SELECT T_.*, ROWNUM R_ FROM (
                SELECT <@fields mainTable['name'] mainTable['alias'] />
                <#list links?keys as t>
                    , <@fields t links[t]['alias'] />
                </#list>
                FROM ${mainTable['name']} ${mainTable['alias']}
                <#list links?keys as t>
                    LEFT OUTER JOIN ${t} ${links[t]['alias']} ON ${links[t]['link']}
                </#list>
                WHERE 1 = 1
                <@where mainTable['name'] mainTable['alias'] />
                <#list links?keys as t>
                    <@where t links[t]['alias'] />
                </#list>
                <@orderby />
            ) T_
            WHERE ROWNUM <= ${regulation['pagination']['offsetEnd']}
        )
        WHERE R_ > ${regulation['pagination']['offsetStart']}
    <#else>
        SELECT <@fields mainTable['name'] mainTable['alias'] />
        <#list links?keys as t>
            , <@fields t links[t]['alias'] />
        </#list>
        FROM ${mainTable['name']} ${mainTable['alias']}
        <#list links?keys as t>
            LEFT OUTER JOIN ${t} ${links[t]['alias']} ON ${links[t]['link']}
        </#list>
        WHERE 1 = 1
        <@where mainTable['name'] mainTable['alias'] />
        <#list links?keys as t>
            <@where t links[t]['alias'] />
        </#list>
        <@orderby />
    </#if>
</#macro>

<#--
mainTable: {name: 'Users', alias: 'u', id:'', type:''}
links: [
                {name: 'Address', alias: 'a', property: 'address', javaType: 'Address'},
              ]
-->


