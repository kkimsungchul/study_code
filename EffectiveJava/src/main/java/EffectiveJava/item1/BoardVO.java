package EffectiveJava.item1;

public class BoardVO {

    private int seq;
    private String title;
    private String content;
    private String writer;

    public BoardVO(Builder builder){
        this.title = builder.title;
        this.content = builder.content;
        this.seq = builder.seq;
        this.writer = builder.writer;
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "seq=" + seq +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                '}';
    }

    public static class Builder{
        private int seq;
        private String title;
        private String content;
        private String writer;

        public Builder setSeq(int seq){
            this.seq = seq;
            return this;
        }

        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        public Builder setContent(String content){
            this.content = content;
            return this;
        }

        public Builder setWriter(String writer){
            this.writer = writer;
            return this;
        }

        public BoardVO build(){
            return new BoardVO(this);
        }



    }



}
