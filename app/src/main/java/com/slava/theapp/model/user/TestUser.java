package com.slava.theapp.model.user;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by slava on 24.10.17.
 */

public class TestUser extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    //private RealmList<TestSomething> user; // Declare one-to-many relationships

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "TestUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestUser testUser = (TestUser) o;

        if (id != testUser.id) return false;
        return name.equals(testUser.name);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }
}
