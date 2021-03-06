package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UserDao {
	@SqlUpdate("create table users (id integer primary key autoincrement, role varchar(30), name varchar(100), alias varchar(100), email varchar(100), passwdHash varchar(32), salt varchar(32))")
	void createUserTable();

	@SqlUpdate("insert into users (role, name,alias,email, passwdHash, salt) values (:role, :name, :alias, :email, :passwdHash, :salt)")
	@GetGeneratedKeys
	int insert(@BindBean() User user);

	@SqlQuery("select * from users where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
	User findByName(@Bind("name") String name);

	@SqlUpdate("drop table if exists users")
	void dropUserTable(); 

	@SqlQuery("select * from users order by id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<User> all();

	@SqlQuery("select * from users where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User findById(@Bind("id") int id);

	@SqlQuery("select * from users where role = :role")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User findByRole(@Bind("role") int role);


	
	void close();
}