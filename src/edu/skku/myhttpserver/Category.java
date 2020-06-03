package edu.skku.myhttpserver;

import java.util.ArrayList;
import java.util.Random;

public class Category {

    private String type_name;
    private ArrayList<Story> stories = new ArrayList<Story>();

    public Category(String type_name){
        this.type_name = type_name;
    }
    public Category(String type_name, ArrayList<Story> stories){
        this.type_name = type_name;
        this.stories = stories;
    }

    public String getTypeName(){
        return this.type_name;
    }

    public Story getStory(){

        Random random = new Random();
        int randome_index = random.nextInt(this.stories.size());
        return stories.get(randome_index);
    }
    public int getStorySize(){
        return this.stories.size();
    }
    public ArrayList<Story> getStories(){ return this.stories; }

}
