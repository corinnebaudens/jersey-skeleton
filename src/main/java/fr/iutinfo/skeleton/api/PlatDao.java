package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface PlatDao {
	@SqlUpdate("create table plats (id integer primary key autoincrement, name varchar(50))")
	void createPlatTable();

	@SqlUpdate("insert into plats (name) values (:name)")
	@GetGeneratedKeys
	int insert(@BindBean() Plat plat);

	@SqlQuery("select * from plats where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
	Plat findByName(@Bind("name") String name);

	@SqlUpdate("drop table if exists plats")
	void dropPlatTable(); 

	@SqlQuery("select * from plats order by id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Plat> all();

	@SqlQuery("select * from plats where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Plat findById(@Bind("id") int id);

	void close();
}
