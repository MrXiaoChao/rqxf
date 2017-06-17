package com.zity.rqxf.bean;

import java.util.List;

/**
 * Created by luochao on 2017/6/17.
 * 代办案件
 */

public class CaseList {

    /**
     * count : 7
     * list : [{"content":"问题及诉求","id":"8311fd32-34bb-49b9-ba64-fa20dc64fb7f","time":"2017-05-31","cases":7,"status":2,"letterPerson":"来访人姓名"},{"content":"123","id":"2863f174-c5b7-4300-9009-3b4223a8a180","time":"2017-05-31","cases":2,"status":3,"letterPerson":"匿名"},{"content":"问题及诉求","id":"75a5b691-063b-4bd9-a37e-e3bbadcbb6a4","time":"2017-06-09","cases":7,"status":2,"letterPerson":"大刀王五"},{"content":"问题及诉求","id":"35efa766-f88d-47e2-9b2b-b057fe9bce22","time":"2017-06-09","cases":7,"status":2,"letterPerson":"燕子李三"},{"content":"问题及诉求","id":"fb6a75bc-0fa2-44e2-ae30-0249c2c44775","time":"2017-06-12","cases":2,"status":3,"letterPerson":"匿名"},{"content":"34","id":"24ea12c7-7415-49c6-b45e-04feb282a451","time":"2017-06-14","cases":2,"status":2,"letterPerson":"匿名"},{"content":"234","id":"90c57ae1-f1f5-429d-82d5-a66310872072","time":"2017-06-14","cases":2,"status":3,"letterPerson":"匿名"}]
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * content : 问题及诉求
         * id : 8311fd32-34bb-49b9-ba64-fa20dc64fb7f
         * time : 2017-05-31
         * cases : 7
         * status : 2
         * letterPerson : 来访人姓名
         */

        private String content;
        private String leterId;
        private String time;
        private int cases;
        private int status;
        private String letterPerson;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getleterId() {
            return leterId;
        }

        public void setleterId(String leterId) {
            this.leterId= leterId;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getCases() {
            return cases;
        }

        public void setCases(int cases) {
            this.cases = cases;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getLetterPerson() {
            return letterPerson;
        }

        public void setLetterPerson(String letterPerson) {
            this.letterPerson = letterPerson;
        }
    }
}
