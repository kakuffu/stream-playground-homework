package brickset;

import repository.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }



    public void printLegoSetAlphabeticalOrder() {
        getAll().stream().
                map(LegoSet::getName).
                sorted(Comparator.nullsFirst(Comparator.naturalOrder())).
                forEach(System.out::println);
    }

    public long maximumPieces(){
        return getAll().stream().
                mapToLong(LegoSet::getPieces).
                max().
                getAsLong();
    }

    public void printNameOfPackagingTypeBox(){
        getAll().stream().
                filter(legoset -> legoset.getPackagingType() == PackagingType.BOX).
                map(LegoSet::getName).
                forEach(System.out::println);
    }
    public void printNamePiecesLessThanHundred(){
        getAll().stream().
                filter(legoset -> legoset.getPieces() < 100).
                forEach(System.out::println);
    }

//    public long sumBricksThemeDuplo(){
//        return getAll().stream().
//                filter(legoset -> legoset.getTheme() == Theme.DUPLO).
//                mapToLong(LegoSet::getPieces).
//                sum();
//    }

    public boolean printIamGayIfDuploExists(){
        /**
         * @param
         * @return prints "I am a Turtle" if the lego set name contains word "duplo"
         */
      return getAll().stream().map(LegoSet::getName).
              anyMatch(name-> name.toLowerCase().contains("duplo"));
    }

    public void printTagsWithGamesTheme(){
        /**
         * @param
         * @return Returns all the tags that have Games as a theme Except tags that are null
         */
         getAll().stream().
                filter(legoSet -> Objects.equals(legoSet.getTheme(), "Games")).
                 filter(brickset -> brickset.getTags()!=null).
                 flatMap(brickset -> brickset.getTags().stream()).
                        forEach(System.out::println);
    }

    public Optional<String> printLongestTagOfLegoSetWithMoreThan500Pieces(){
        /**
         * @param
         * @return Returns the longest lego set name that has more than 500 pieces
         */
       return getAll().stream().
               filter(legoSet -> legoSet.getPieces()>500).
               map(LegoSet::getName).
               filter(Objects::nonNull).
               reduce((name1, name2) -> name1.length() > name2.length()? name1 : name2);
    }

    public Map<Integer, Set<String>> printNumberOfPiecesAndThemeOfLegoSet(){
        /**
         * @param
         * @return a map with number of pieces as a key and set of string as a value
         */
        return getAll().stream().
                collect(groupingBy(LegoSet::getPieces,mapping(LegoSet::getTheme, toSet())));
    }

    public Map<String, Set<PackagingType>> printNameAndPackagingTypeOfLegoSetWithThemeStarWars() {
        /**
         * @param
         * @return a map with number of pieces as a key and set of string as a value
         */
        return getAll().stream()
                .filter(legoSet -> legoSet.getName() != null)
                .distinct()
                .filter(legoSet -> legoSet.getTheme() != null && legoSet.getTheme().equals("Star Wars"))
                .collect(groupingBy(LegoSet::getName, mapping(LegoSet::getPackagingType, toSet())));}


    public static void main(String[] args) {
        var repository = new LegoSetRepository();

        System.out.println("**Q1******************************************");
        if (repository.printIamGayIfDuploExists())
            System.out.println("I am turtle!");
        System.out.println("**Q2******************************************");
        repository.printTagsWithGamesTheme();
        System.out.println("**Q3******************************************");
        System.out.println(repository.printLongestTagOfLegoSetWithMoreThan500Pieces());
        System.out.println("**Q4*****************************************");
        System.out.println(repository.printNumberOfPiecesAndThemeOfLegoSet());
        System.out.println("**Q5*****************************************");
        System.out.println(repository.printNameAndPackagingTypeOfLegoSetWithThemeStarWars());

    }


}





