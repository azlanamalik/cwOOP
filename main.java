class main{
public static void main(String [] args){
    Race race = new Race(30);
    Horse horse1 = new Horse('x',"1",0.8);
    Horse horse2 = new Horse('x',"2",0.7);
    Horse horse3 = new Horse('x',"3",0.75);
    race.addHorse(horse1,1);
    race.addHorse(horse2,2);
    race.addHorse(horse3,3);
    race.startRace();
}}