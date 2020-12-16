package info.anastasios.blog.bll;

import info.anastasios.blog.bo.Member;
import info.anastasios.blog.bo.Post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestBll {

    public static void main(String[] args) throws SQLException {
        MemberManager memberManager = MemberManager.getInstance();
        Member member = memberManager.getMemberById(1);
        System.out.println(member.toString());

        System.out.println();

        List<Member> memberList = new ArrayList<>();
        memberList = memberManager.getMembers();
        for (Member i : memberList) {
            System.out.println(i);
        }

        System.out.println();

        PostManager postManager = PostManager.getInstance();
        List<Post> postList = new ArrayList<>();
        postList = postManager.getPostByMemberId(1);
        for (Post j : postList) {
            System.out.println(j);
        }

        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println();

        /*
        Member betty = new Member("Betty", "Moor", "betty@gmail.com", "333");
        Member tmpMember = memberManager.putMember(betty);
        System.out.println(tmpMember.toString()); */

        Post post1 = new Post("Test more", "yesssssssssssssssssssssssssss", "20-05-2020", member);
        Post insertedPost = postManager.putPost(post1);
        System.out.println(insertedPost.toString());

    }

}
