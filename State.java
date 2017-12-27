/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.problem;

import java.util.ArrayList;
/**
 * @author Simge Haksal
 */
public class State {
    private int cannibalLeft;
    private int missionaryLeft;
    private int cannibalRight;
    private int missionaryRight;
    private boolean goal;
    public ArrayList<State> child;
    private State parent;

    public State() {
        this.child = new ArrayList<>();
        this.parent = null;
        this.cannibalLeft = 0;
        this.missionaryLeft = 0;
        this.cannibalRight = 3;
        this.missionaryRight = 3;
        this.goal = false;
    }
    
    public State(int cannibalLeft, int missionaryLeft, 
    int cannibalRight, int missionaryRight, State parent){
        this.parent = parent;
        this.child = new ArrayList<>();
        this.cannibalLeft = cannibalLeft;
        this.missionaryLeft = missionaryLeft;
        this.cannibalRight = cannibalRight;
        this.missionaryRight = missionaryRight;
    }
    
    public boolean isGoal() {
            return (this.getCannibalLeft() == 3 && this.getMissionaryLeft() == 3);
    }

    public boolean isValid() {
        return missionaryLeft>=cannibalLeft && missionaryRight>=cannibalRight;
    }
    
    public boolean isValidOption(int cannibalLeft,int missionaryLeft,
    int cannibalRight, int missionaryRight ) {
        if(missionaryLeft == 0 ){
            if ( missionaryRight>=cannibalRight ) {
                return true;
            }
        }
        if(missionaryRight == 0 ){
            if ( missionaryLeft>=cannibalLeft ) {
                return true;
            }
        }
        return missionaryLeft>=cannibalLeft && missionaryRight>=cannibalRight;
    }
    
   /*public State doThing(State s){
       findGoal(s);
       if(!s.getGoal()){
           for(int i = 0; i<s.child.size(); i++){
                if(s.child.get(i).getGoal()){
                    return s.child.get(i);
                }
                else{
                    findGoal(s.child.get(i));
                    
                }
            }          
       }
       else{
           return s;
       }
   }*/

   /* public void findGoal(State s){
            s.generateStates();

            for(int i = 0; i < s.child.size(); i++){
                
                if(isGoal(s.child.get(i))){
                    s.setGoal(true);
                    System.out.println("Goal Node!!");
                }
            }
    }*/
    
    public void generateStates() {   

        if(this.cannibalRight > 0 && isValidOption(this.cannibalLeft+1,this.missionaryLeft,
                         this.cannibalRight-1,this.missionaryRight)){
            State generations = new State(cannibalLeft+1, missionaryLeft,
                                cannibalRight-1, missionaryRight, this);
            this.child.add(generations);
        } //two cannibal on the boat
        
        if(this.missionaryRight>0 && isValidOption(this.cannibalLeft,this.missionaryLeft+1,
                         this.cannibalRight,this.missionaryRight-1)){
            State generations = new State(cannibalLeft, missionaryLeft+1,
                                cannibalRight, missionaryRight-1, this);
            this.child.add(generations);
        } //two missionary on the boat
        
        if(this.missionaryLeft>0 && this.cannibalRight>0 && 
           isValidOption(this.cannibalLeft+1,this.missionaryLeft-1,
           this.cannibalRight-1,this.missionaryRight+1)){
                State generations = new State(cannibalLeft+1, missionaryLeft-1,
                                    cannibalRight-1, missionaryRight+1, this);
                this.child.add(generations);
        } //one cannibal goes left one missionary goes right
        
        if(this.cannibalLeft>0 && this.missionaryRight>0 && 
           isValidOption(this.cannibalLeft-1,this.missionaryLeft+1,
           this.cannibalRight+1,this.missionaryRight-1)){
                State generations = new State(cannibalLeft-1, missionaryLeft+1,
                                    cannibalRight+1, missionaryRight-1, this);
                this.child.add(generations);
        } //one cannibal goes right one missionary goes left
    }
    public void setGoal(boolean g){
        this.goal = g;
    }
    public boolean getGoal(){
        return this.goal;
    }
    
    public State getParentState(){
        return this.parent;
    }
    public int getCannibalLeft() {
        return cannibalLeft;
    }

    public void setCannibalLeft(int cannibalLeft) {
        this.cannibalLeft = cannibalLeft;
    }

    public int getMissionaryLeft() {
        return missionaryLeft;
    }

    public void setMissionaryLeft(int missionaryLeft) {
        this.missionaryLeft = missionaryLeft;
    }

    public int getCannibalRight() {
        return cannibalRight;
    }

    public void setCannibalRight(int cannibalRight) {
        this.cannibalRight = cannibalRight;
    }

    public int getMissionaryRight() {
        return missionaryRight;
    }

    public void setMissionaryRight(int missionaryRight) {
        this.missionaryRight = missionaryRight;
    }
    
    public void printChildList(){
        for(int i = 0 ; i < this.child.size(); i++){
            System.out.println("Missionary left: " + this.child.get(i).getMissionaryLeft() + 
                    " cannibal left: " + this.child.get(i).getCannibalLeft() + 
                    " missionary right: " + this.child.get(i).getMissionaryRight() + 
                    " Cannibal right: " + this.child.get(i).getCannibalRight());
        }
    }
}
