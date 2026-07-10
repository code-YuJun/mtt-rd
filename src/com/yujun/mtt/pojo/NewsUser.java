package com.yujun.mtt.pojo;

// 数据库中的一行记录 ↔ Java 中的一个对象。
// 全参构造器
import lombok.AllArgsConstructor;
// Getter、Setter、toString()、equals()、hashCode()
import lombok.Data;
// 无参构造器
import lombok.NoArgsConstructor;
// 序列化接口
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsUser implements Serializable {
    private Integer uid;
    private String username;
    private String userPwd;
    private String nickName;
}

/**
 * lombok.AllArgsConstructor
 * 相当于：
 * public NewsUser(Integer uid, String username, String userPwd, String nickName) {
 *     this.uid = uid;
 *     this.username = username;
 *     this.userPwd = userPwd;
 *     this.nickName = nickName;
 * }
 */

/**
 * lombok.NoArgsConstructor
 * 相当于：
 * public NewsUser() { }
 */

/**
 *  Serializable 标记接口，里面什么方法都没有
 *  public interface Serializable {}，告诉 JVM：这个对象允许被序列化。
 *  序列化：把对象变成可以保存、传输的数据。
 *  对象：NewsUser user = new NewsUser(1,"admin","123456","管理员");
 *  内存中其实长这样：
 *  uid -------> 1
 *  username --> admin
 *  password --> 123456
 *  nickName --> 管理员
 *  但是网络不能传对象。文件不能保存对象。数据库不能直接存对象。
 *  所以要先变成这样：010101001010010101... 或者 JSON 形式，这种事可以传输的，这个过程就是序列化，收到数据之后再恢复对象，这个过程就是反序列化。
 *
 */
