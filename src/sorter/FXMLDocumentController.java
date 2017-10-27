/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorter;

import java.net.URL;
import java.util.ArrayList;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author aarim
 */
public class FXMLDocumentController implements Initializable {
    
    private List<Integer> numbers = new ArrayList<>();
    private ObservableList<Integer> obsNumbers = FXCollections.observableList(numbers);
    private List<String> algoList = new ArrayList<>();
    private ObservableList<String> obsAlgoList = FXCollections.observableList(algoList);
    @FXML
    private Label label;
    public RadioButton entRadio;
    public RadioButton genRadio;
    public ComboBox selAlg;
    public ListView unsortedNumb;
    public ListView sortedNumb;
    public TextField enterNumb;
    public TextField ranAmount;
    public TextField ranMin;
    public TextField ranMax;
    public Button add;
    public Button del;
    public ProgressBar progress;
    public Label time;
    public Label status;
    public Button clear;
    @FXML
    private void switchGenMode(ActionEvent event){
        if(event.getSource()==entRadio){
            System.out.println("Enter Numbers Selected");
            entRadio.setSelected(true);
            genRadio.setSelected(false);
            ranAmount.setDisable(true);
            ranMin.setDisable(true);
            ranMax.setDisable(true);
            add.setDisable(false);
            del.setDisable(false);
            enterNumb.setDisable(false);
            clear.setDisable(false);
        }else{
            System.out.println("Generate Numbers Selected");
            entRadio.setSelected(false);
            genRadio.setSelected(true);
            ranAmount.setDisable(false);
            ranMin.setDisable(false);
            ranMax.setDisable(false);
            add.setDisable(true);
            del.setDisable(true);
            enterNumb.setDisable(true);
            clear.setDisable(true);
        }
    }
    @FXML
    private void addNumb(ActionEvent event){
       try{
        obsNumbers.add(Integer.parseInt(enterNumb.getText()));
        enterNumb.setText("");
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Valid Number");
            alert.setContentText("Please Enter A Valid Number.");
            alert.showAndWait();
        }
    }
    @FXML
    private void delNumb(ActionEvent event){
        if(unsortedNumb.getSelectionModel().getSelectedIndex() >= 0){
            
       obsNumbers.remove(unsortedNumb.getSelectionModel().getSelectedIndex());
               }
    }
    @FXML
    private void clearNumb(ActionEvent event){
        obsNumbers.clear();
    }
    @FXML
    private void enterTextKeyPressed(KeyEvent event){
        System.out.println("Key PRessed"+event.getCode().getName());
        if(event.getCode() == KeyCode.ENTER){
            addNumb(null);
        }
    }
    
    @FXML
    private void sort(ActionEvent event){
        progress.setProgress(0);
        sortedNumb.setItems(null);
        if(genRadio.isSelected()){
            
            try{
                obsNumbers.clear();
            double amount = Integer.parseInt(ranAmount.getText());
            for(int i = 0;i<amount;i++){
                double range = (Integer.parseInt(ranMax.getText()) - Integer.parseInt(ranMin.getText())) + 1;     
                obsNumbers.add((int)(Math.random() * range) + Integer.parseInt(ranMin.getText()));
            }
            }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not Valid Numbers");
            alert.setContentText("Please Enter Valid Numbers.");
            alert.showAndWait();
            }
        }
        int[] array = toIntArray(numbers);
       
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(selAlg.getValue());
        if(selAlg.getValue().equals(obsAlgoList.get(0))){
           ArrayVerwalter.insertionSort(array);
        }else if(selAlg.getValue().equals(obsAlgoList.get(1))){
          ArrayVerwalter.selectionSort(array);
        }else if(selAlg.getValue().equals(obsAlgoList.get(2))){
            ArrayVerwalter.bubbleSort(array);
        }else if(selAlg.getValue().equals(obsAlgoList.get(3))){
            ArrayVerwalter.mergeSort(array, 0, array.length-1);
        }else if(selAlg.getValue().equals(obsAlgoList.get(4))){
            ArrayVerwalter.shakerSort(array);
        }else if(selAlg.getValue().equals(obsAlgoList.get(5))){
            ArrayVerwalter.gnomeSort(array);
        }else{
            try{
            ArrayVerwalter.swapSort(array);
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Valid Array");
            alert.setContentText("The selected algorithm does not support multiple elements with the same value!");
            alert.show();
            }
        }
        
        
        time.setText((System.currentTimeMillis()-currentTimeMillis)+"ms");
        Integer[] integerArray = new Integer[array.length];
        int i = 0;
for(int intValue : array) {
    integerArray[i++] = intValue;
}
 
        sortedNumb.setItems(FXCollections.observableArrayList(new ArrayList<Integer>(Arrays.asList(integerArray))));
        progress.setProgress(1);
        if(ArrayVerwalter.isSorted(array)){
            status.setTextFill(Color.GREEN);
           status.setText("Finished"); 
        }else{
             status.setTextFill(Color.RED);
            status.setText("ERROR");
        }
        
    

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         obsAlgoList.add("Insertionsort");
         obsAlgoList.add("Selectionsort");
         obsAlgoList.add("Bubblesort");
         obsAlgoList.add("Mergesort");
         obsAlgoList.add("Shakersort");
         obsAlgoList.add("Gnomesort");
         obsAlgoList.add("Swap-Sort");
        selAlg.setItems(obsAlgoList);
        selAlg.setValue(obsAlgoList.get(0));
        
        unsortedNumb.setItems(obsNumbers);
    }    
    private int[] toIntArray(List<Integer> list){
  int[] array = new int[list.size()];
  for(int i = 0;i < array.length;i++)
    array[i] = list.get(i);
  return array;
}
    
}
