package com.zity.rqxf.bean;

import java.util.List;

/**
 * Created by luochao on 2017/7/6.
 * 反馈历史
 */

public class Fkls {

    /**
     * count : 3
     * list : [{"content":"sdsdsdsd","id":"f649bb29-561d-4a3c-956d-7357ffb9785b","feedbackdate":"2017-07-06","name":"组织部办案人","participant":"dsdsd","working":"电话"},{"content":"sdfsdfs","id":"8b17892f-d977-4745-b690-7114bd08e248","feedbackdate":"2017-07-06","name":"组织部办案人","participant":"fdsf","working":"电话"},{"content":"dsfsfsd","id":"4f9e6403-0cad-4b34-8160-a626e9207886","feedbackdate":"2017-07-06","name":"组织部办案人","participant":"dsfsfds","working":"电话"}]
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
         * content : sdsdsdsd
         * id : f649bb29-561d-4a3c-956d-7357ffb9785b
         * feedbackdate : 2017-07-06
         * name : 组织部办案人
         * participant : dsdsd
         * working : 电话
         */

        private String content;
        private String id;
        private String feedbackdate;
        private String name;
        private String participant;
        private String working;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFeedbackdate() {
            return feedbackdate;
        }

        public void setFeedbackdate(String feedbackdate) {
            this.feedbackdate = feedbackdate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParticipant() {
            return participant;
        }

        public void setParticipant(String participant) {
            this.participant = participant;
        }

        public String getWorking() {
            return working;
        }

        public void setWorking(String working) {
            this.working = working;
        }
    }
}
