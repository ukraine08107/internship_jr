package com.game.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Player.class)
public abstract class Player_ {
    public static volatile SingularAttribute<Player, Long> id;
    public static volatile SingularAttribute<Player, String> name;
    public static volatile SingularAttribute<Player, String> title;
    public static volatile SingularAttribute<Player, Race> race;
    public static volatile SingularAttribute<Player, Profession> profession;
    public static volatile SingularAttribute<Player, Integer> experience;
    public static volatile SingularAttribute<Player, Integer> level;
    public static volatile SingularAttribute<Player, Integer> untilNextLevel;
    public static volatile SingularAttribute<Player, Date> birthday;
    public static volatile SingularAttribute<Player, Boolean> banned;
}
