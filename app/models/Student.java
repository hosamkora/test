package models;

import java.util.HashSet;
import java.util.Set;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;

@Entity
public class Student extends Model{
    @Id
    public Integer id;
    public String name;
    public Integer age;

    // Integer : is type of primary key , it can be any type as you want @Id
    //Student : is type of Model
    public static Finder<Integer,Student> find = new Finder<>(Student.class);


}
