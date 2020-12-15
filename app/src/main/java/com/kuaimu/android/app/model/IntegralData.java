package com.kuaimu.android.app.model;

import java.util.List;

public class IntegralData {

    /**
     * code : 200
     * msg : 成功
     * data : {"total":"20","data":{"current_page":1,"data":[{"id":2,"created_at":"2020-12-13 16:03:07","updated_at":"2020-12-13 16:03:07","tourist_id":28,"num":10,"type":0,"type_name":"28"},{"id":1,"created_at":"2020-12-13 15:41:52","updated_at":null,"tourist_id":28,"num":10,"type":1,"type_name":"乐币充值"}],"first_page_url":"https://kuaimutj.com/api/person/integral?page=1","from":1,"last_page":1,"last_page_url":"https://kuaimutj.com/api/person/integral?page=1","next_page_url":null,"path":"https://kuaimutj.com/api/person/integral","per_page":"10","prev_page_url":null,"to":2,"total":2}}
     */

    private int code;
    private String msg;
    private DataBeanXX data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBeanXX getData() {
        return data;
    }

    public void setData(DataBeanXX data) {
        this.data = data;
    }

    public static class DataBeanXX {
        /**
         * total : 20
         * data : {"current_page":1,"data":[{"id":2,"created_at":"2020-12-13 16:03:07","updated_at":"2020-12-13 16:03:07","tourist_id":28,"num":10,"type":0,"type_name":"28"},{"id":1,"created_at":"2020-12-13 15:41:52","updated_at":null,"tourist_id":28,"num":10,"type":1,"type_name":"乐币充值"}],"first_page_url":"https://kuaimutj.com/api/person/integral?page=1","from":1,"last_page":1,"last_page_url":"https://kuaimutj.com/api/person/integral?page=1","next_page_url":null,"path":"https://kuaimutj.com/api/person/integral","per_page":"10","prev_page_url":null,"to":2,"total":2}
         */

        private String total;
        private DataBeanX data;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public DataBeanX getData() {
            return data;
        }

        public void setData(DataBeanX data) {
            this.data = data;
        }

        public static class DataBeanX {
            /**
             * current_page : 1
             * data : [{"id":2,"created_at":"2020-12-13 16:03:07","updated_at":"2020-12-13 16:03:07","tourist_id":28,"num":10,"type":0,"type_name":"28"},{"id":1,"created_at":"2020-12-13 15:41:52","updated_at":null,"tourist_id":28,"num":10,"type":1,"type_name":"乐币充值"}]
             * first_page_url : https://kuaimutj.com/api/person/integral?page=1
             * from : 1
             * last_page : 1
             * last_page_url : https://kuaimutj.com/api/person/integral?page=1
             * next_page_url : null
             * path : https://kuaimutj.com/api/person/integral
             * per_page : 10
             * prev_page_url : null
             * to : 2
             * total : 2
             */

            private int current_page;
            private String first_page_url;
            private int from;
            private int last_page;
            private String last_page_url;
            private Object next_page_url;
            private String path;
            private String per_page;
            private Object prev_page_url;
            private int to;
            private int total;
            private List<DataBean> data;

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public String getFirst_page_url() {
                return first_page_url;
            }

            public void setFirst_page_url(String first_page_url) {
                this.first_page_url = first_page_url;
            }

            public int getFrom() {
                return from;
            }

            public void setFrom(int from) {
                this.from = from;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
            }

            public String getLast_page_url() {
                return last_page_url;
            }

            public void setLast_page_url(String last_page_url) {
                this.last_page_url = last_page_url;
            }

            public Object getNext_page_url() {
                return next_page_url;
            }

            public void setNext_page_url(Object next_page_url) {
                this.next_page_url = next_page_url;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getPer_page() {
                return per_page;
            }

            public void setPer_page(String per_page) {
                this.per_page = per_page;
            }

            public Object getPrev_page_url() {
                return prev_page_url;
            }

            public void setPrev_page_url(Object prev_page_url) {
                this.prev_page_url = prev_page_url;
            }

            public int getTo() {
                return to;
            }

            public void setTo(int to) {
                this.to = to;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * id : 2
                 * created_at : 2020-12-13 16:03:07
                 * updated_at : 2020-12-13 16:03:07
                 * tourist_id : 28
                 * num : 10
                 * type : 0
                 * type_name : 28
                 */

                private int id;
                private String created_at;
                private String updated_at;
                private int tourist_id;
                private int num;
                private int type;
                private String type_name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public int getTourist_id() {
                    return tourist_id;
                }

                public void setTourist_id(int tourist_id) {
                    this.tourist_id = tourist_id;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getType_name() {
                    return type_name;
                }

                public void setType_name(String type_name) {
                    this.type_name = type_name;
                }
            }
        }
    }
}
