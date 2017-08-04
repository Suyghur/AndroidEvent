package event.imy.application;

/**
 * Created by 4399-蒋明伟 on 2017/8/3.
 */

public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public User(String name) {
        this.name = name;
    }
}
