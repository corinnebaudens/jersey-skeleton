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
	void createCommandeTable();
	
	@SqlUpdate("insert into commandes (idclient,idplat,quantitecde) values (:id_client, :id_plat, :quantitecde)")
	@GetGeneratedKeys
	int insert(@BindBean() Commande commande);
	
	@SqlQuery("select sum(quantitecde) from commandes where idplat = :idplat")
	int quantiteCommande(@Bind("idplat") int idplat );
	
	@SqlUpdate("drop table if exists commandes")
	void dropCommandeTable();

	@SqlQuery("select * from commandes where idclient = :idclient")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Commande> listeCdeParClient();
	
}
