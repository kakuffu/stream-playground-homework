package brickset;

import repository.Repository;

import java.util.Comparator;
import java.util.stream.Collectors;


/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    public long countLegoSetsWithLessThan500() {
        return getAll().stream()
                .filter(legoSet -> legoSet.getPieces() < 500)
                .count();
        /**
         * Returns the number of LEGO sets with pieces less than 500.
         *
         * @param
         * @return the number of LEGO sets with pieces less than 500
         */
    }

    public long sumOfAllLegoSetPieces() {
        /**
         * Returns the sum of all the lego pieces
         *
         * @param
         * @return sum of all the lego pieces
         */
        return getAll().stream().
                filter(legoSet -> legoSet.getPackagingType() == PackagingType.BOX).
                mapToLong(LegoSet::getPieces).
                sum();
    }

    public void legoSetsStartingWithI() {
        /**
         * Returns the lego sets starting with "I"
         *
         * @param
         * @return the lego sets starting with "I"
         */
        getAll().stream().
                map(LegoSet::getName).
                filter(name -> name.startsWith("I")).
                forEach(System.out::println);
    }
    public double averageLengthOfTheCountryNames(){
        /**
         * Calculates the average length of country names
         *
         * @param
         * @return average length of country names
         */
        return getAll().stream().
                map(LegoSet::getName).
                mapToInt(String::length).
                average().
                getAsDouble();
    }

    public String legoSetWithLongestName(){
        /**
         * Lego set with the longest name
         *
         * @param
         * @return Lego set with the longest name
         */
        return getAll().stream().
                map(LegoSet::getName).
                max(Comparator.comparingInt(String::length)).
                get();
    }
    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println("The number of LEGO sets with pieces less than 500: " + repository.countLegoSetsWithLessThan500());
        System.out.println("*********************************************************");
        System.out.println("Sum of all the lego pieces: " + repository.sumOfAllLegoSetPieces());
        System.out.println("***********************************");
        System.out.println("The lego sets starting with \"I\": ");
        repository.legoSetsStartingWithI();
        System.out.println("***************************************************");
        System.out.println("Average length of country names: " + repository.averageLengthOfTheCountryNames());
        System.out.println("***********************************************************************************************");
        System.out.println("Lego Set with longest name: " + repository.legoSetWithLongestName());
    }

}






