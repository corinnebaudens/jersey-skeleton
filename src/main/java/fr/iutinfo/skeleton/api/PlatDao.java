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
	@SqlUpdate("create table plats (id integer primary key autoincrement, nom varchar(50), cuisinier varchar(50),quantitePart integer(2))")
	void createPlatTable();

	@SqlUpdate("insert into plats (nom,cuisinier,quantitePart) values (:nom, :cuisinier, :quantitePart)")
	@GetGeneratedKeys
	int insert(@BindBean() Plat plat);

	@SqlQuery("select * from plats where nom like :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
	List<Plat> trouveParNom(@Bind("nom") String nom);

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
