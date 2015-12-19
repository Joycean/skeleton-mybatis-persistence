<#assign baseColumns = {
    'id': {'javaType': 'long', 'jdbcType': 'BIGINT'},
    'insertTime': {'javaType': 'java.util.Date', 'jdbcType': 'TIMESTAMP'},
    'updateTime': {'javaType': 'java.util.Date', 'jdbcType': 'TIMESTAMP'},
    'insertBy': {'javaType': 'String', 'jdbcType': 'VARCHAR'},
    'updateBy': {'javaType': 'String', 'jdbcType': 'VARCHAR'}
}>


<#assign tableColumns = {
    'Users' : {
        'name': {'javaType': 'String', 'jdbcType': 'VARCHAR'},
        'password': {'javaType': 'String', 'jdbcType': 'VARCHAR'},
        'email': {'javaType': 'String', 'jdbcType': 'VARCHAR'},
        'role': {'javaType': 'String', 'jdbcType': 'VARCHAR'}
    },
    
    'Group' : {
        'name': {'javaType': 'String', 'jdbcType': 'VARCHAR'}
    }
    
}
>