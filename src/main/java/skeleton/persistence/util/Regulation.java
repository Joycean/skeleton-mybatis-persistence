package skeleton.persistence.util;

import java.util.ArrayList;
import java.util.List;

public class Regulation {
    public List<Sort> sorts = new ArrayList<Sort>();
    public Pagination pagination;
    
    public List<Sort> getSorts() {
        return sorts;
    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }

    public void addSort(Sort sort) {
        this.sorts.add(sort);
    }
    
    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }



    public static class Sort {
        public static final String ASC = "ASC";
        public static final String DESC = "DESC";
        private String column;
        private String order = ASC;

        public Sort() {
        }

        public Sort(String column, String order) {
            this.column = column;
            this.order = order;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
    
    public static class Pagination {
        public static final String PARAMETER_PAGE = "page";
        public static final String PARAMETER_PAGE_SIZE = "pageSize";
    	
    	private int page = 1;
        private int pageCount = 0;
        private int rowPerPage = 50;
        private int rowCount = 0;
        private int currentPageRow = 0;
        
        public Pagination(int page, int rowPerPage) {
            super();
            this.setPage(page);
            this.setRowPerPage(rowPerPage);
        }

        public int getOffsetStart() {
            return (page - 1) * rowPerPage;
        }
        
        public int getOffsetEnd() {
            return page * rowPerPage;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page <= 0 ? 1 : page;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getRowPerPage() {
            return rowPerPage;
        }

        public void setRowPerPage(int rowPerPage) {
            this.rowPerPage = rowPerPage;
        }

        public int getRowCount() {
            return rowCount;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;

            pageCount = (int) Math.ceil((float) rowCount / rowPerPage);
            if (page > pageCount) {
                this.setPage(pageCount);
            }
        }
        
        public int getCurrentPageRow() {
            return currentPageRow;
        }

        public void setCurrentPageRow(int currentPageRow) {
            this.currentPageRow = currentPageRow;
        }
    }
    
}