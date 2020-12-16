package info.anastasios.blog.bo;

public class Post {

    private int postId;
    private String title;
    private String body;
    private String date;
    private Member member;

    public Post() {}

    public Post(String title, String body, String date, Member member) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.member = member;
    }

    public Post(int postId, String title, String body, String date, Member member) {
        this.postId = postId;
        this.title = title;
        this.body = body;
        this.date = date;
        this.member = member;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", member=" + member +
                '}';
    }
}
