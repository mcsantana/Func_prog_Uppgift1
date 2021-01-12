import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * created by Mimi Santana
 * Date: 2021-01-04
 * Time: 22:00
 * Project: InlämningsuppgiftFunkProg
 * Copyright: MIT
 */
public class RewriteMe {

    public Database database = new Database();
    public List<Question> questions = database.getQuestions();

    //Skriv en funktioner som returnerar hur många frågor det finns i databasen?
    public int getAmountOfQuestionsInDatabase(){
        return questions.stream().map(Question::getQuestionString).collect(Collectors.toList()).size();

    }

    //Hur många frågor finns i databasen för en viss, given kategori (som ges som inparameter)
    public int getAmountOfQuestionsForACertainCategory(Category category){
        return questions.stream().filter(s-> s.getCategory().equals(category))
                .map(Question::getQuestionString).collect(Collectors.toList()).size();

    }

    //Skapa en lista innehållandes samtliga frågesträngar i databasen
    public List<String> getListOfAllQuestions(){

        return questions.stream().map(Question::getQuestionString).collect(Collectors.toList());
    }

    //Skapa en lista innehållandes samtliga frågesträngar där frågan tillhör en viss kategori
    //Kategorin ges som inparameter
    public List<String> getAllQuestionStringsBelongingACategory(Category category){
        return questions.stream().filter(s-> s.getCategory().equals(category))
                .map(Question::getQuestionString).collect(Collectors.toList());

    }

    //Skapa en lista av alla svarsalternativ, där varje svarsalternativ får förekomma
    // en och endast en gång i den lista som du ska returnera
    public List<String> getAllAnswerOptionsDistinct(){

       return questions.stream().flatMap(s-> s.answers.stream()).distinct().collect(Collectors.toList());
    }


    //Finns en viss sträng, given som inparameter, som svarsalternativ till någon fråga i vår databas?
    public boolean isThisAnAnswerOption(String answerCandidate){
        return questions.stream().map(Question::getAllAnswers).anyMatch(s-> s.contains(answerCandidate));

    }

    //Hur ofta förekommer ett visst svarsalternativ, givet som inparameter, i databasen
    public int getAnswerCandidateFrequncy(String answerCandidate){

        return questions.stream().filter(s-> s.getAllAnswers().contains(answerCandidate))
                .collect(Collectors.toList()).size();

    }

    //Skapa en Map där kategorierna är nycklar och värdena är en lista
    //av de frågesträngar som tillhör varje kategori
    public Map<Category, List<String>> getQuestionGroupedByCategory(){
        return questions.stream().collect(groupingBy(Question::getCategory,mapping(Question::getQuestionString,toList())));

    }

    //Skapa en funktion som hittar det svarsalternativ som har flest bokstäver, i en kategori, given som inparameter
    // OBS: Du måste använda Reduce!
    public String getLongestLettercountAnwerInAGivenCategory(Category c)
    {
        return questions.stream().filter(s -> s.getCategory().equals(c)).flatMap(s -> s.getAllAnswers().stream()).reduce("", (a, b) -> a.length() > b.length() ? a : b);


    }


    public static void main(String[] args){
        RewriteMe uppg4 = new RewriteMe();

    }

}
