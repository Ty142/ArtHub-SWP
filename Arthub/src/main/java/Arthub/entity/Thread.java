package Arthub.entity;

import java.time.LocalDateTime;

public class Thread {
        private int threadID;
        private String titleThread;
        private String threadDescription;
        private int likes;
        private int comments;
        private LocalDateTime dateCreated;
        private int topicID;
        private int userID;

        // Constructor


    public Thread() {
    }

    public int getThreadID() { return threadID; }
        public void setThreadID(int threadID) { this.threadID = threadID; }

        public String getTitleThread() { return titleThread; }
        public void setTitleThread(String titleThread) { this.titleThread = titleThread; }

        public String getThreadDescription() { return threadDescription; }
        public void setThreadDescription(String threadDescription) { this.threadDescription = threadDescription; }

        public int getLikes() { return likes; }
        public void setLikes(int likes) { this.likes = likes; }

        public int getComments() { return comments; }
        public void setComments(int comments) { this.comments = comments; }

        public LocalDateTime getDateCreated() { return dateCreated; }
        public void setDateCreated(LocalDateTime dateCreated) { this.dateCreated = dateCreated; }

        public int getTopicID() { return topicID; }
        public void setTopicID(int topicID) { this.topicID = topicID; }

        public int getUserID() { return userID; }
        public void setUserID(int userID) { this.userID = userID; }
}
