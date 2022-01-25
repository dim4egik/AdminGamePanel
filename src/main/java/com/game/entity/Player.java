package com.game.entity;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "player")
public class Player {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private  String name;

private String title;
@Enumerated(EnumType.STRING)
private Race race;
@Enumerated(EnumType.STRING)
private Profession profession;
   // @Column(name="experience")
private Integer experience;
//private final Integer minExperience = 0;
//private final Integer maxExperience = 10_000_000;
   // @Column(name="level")
private Integer level;
    // private final Integer minLevel = 0;
  //  private final Integer maxLevel = Integer.MAX_VALUE;
   // @Column(name="untiNextLevel")
private Integer untilNextLevel;
   // @Column(name="birthday")
private Date birthday;
   // @Column(name="banned")
private Boolean banned;

    public Player(Long id,String name,String title, Race race, Profession profession,Integer experience,Integer level, Integer untilNextLevel, Date birthday, Boolean banned) {
       this.id = id;
       this.name = name;
       this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.level = levelCalculation(experience);
        this.untilNextLevel = untilNextLeveCalculation(levelCalculation(experience),experience);
        this.birthday = birthday;
        this.banned = banned;
    }
//constructor non-banned
public Player(Long id,String name,String title, Race race, Profession profession,Integer experience,Integer level, Integer untilNextLevel, Date birthday) {
    this.id = id;
    this.name = name;
    this.title = title;
    this.race = race;
    this.profession = profession;
    this.experience = experience;
    this.level = levelCalculation(experience);
    this.untilNextLevel = untilNextLeveCalculation(levelCalculation(experience),experience);
    this.birthday = birthday;
    this.banned = false;
}

    public Player() {
    }
public  Long getId(){return id;}



    public void setId(Long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public Race getRace() {return race;}
    public void setRace(Race race) {this.race = race;}
    public Profession getProfession() {return profession;}
    public void setProfession(Profession profession) {this.profession = profession;}
    public Integer getExperience() {return experience;}
    public void setExperience(Integer experience) {
        this.experience = experience;
    }
    public Integer getLevel() {return level;}
    public void setLevel(Integer experience) {this.level = levelCalculation(experience);}
    public Integer getUntilNextLevel() {return untilNextLevel;}
    public void setUntilNextLevel(Integer experience) {this.untilNextLevel = untilNextLeveCalculation(levelCalculation(experience),experience);}
    public Date getBirthday() {return birthday;}
    public void setBirthday(Date birthday) {this.birthday = birthday;}
    public Boolean isBanned() {return banned;}
    public void setBanned(Boolean banned) {this.banned = banned;}

    private Integer levelCalculation(Integer experience){
        Double squareRoot = Double.valueOf(2500 + 200 *experience);
    Integer square =(int) Math.round(Math.sqrt(squareRoot));
    Integer result = (square - 50) / 100;
    return result;
    }
    private Integer untilNextLeveCalculation(Integer level, Integer experience){
     Integer result = 50 * (level + 1) * (level + 2) - experience;
     return result;

    }

}
