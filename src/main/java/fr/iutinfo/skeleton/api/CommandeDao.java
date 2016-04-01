package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface CommandeDao {
	@SqlUpdate("create table commandes (id integer primary key autoincrement, idclient integer(6), idplat integer(6), quantitecde integer(2))")
	void createCommande();
	
	@SqlUpdate("insert into commandes (idclient,idplat,quantitecde) values (:id_client, :id_plat, :quantité")
	void insert(@BindBean() Commande commande);
	
}
