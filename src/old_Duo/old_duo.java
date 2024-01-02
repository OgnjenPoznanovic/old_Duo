package old_Duo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class old_duo {

	public class Duo{
	    public static void main(String[] args) {
	    	

			Scanner scanner = new Scanner(System.in);
			int kraj= 0;

			do {
				System.out.println("\n");
				System.out.println("Odaberi zadatak:");
				System.out.println("1 - dodati rec");	
				System.out.println("2 - vezbati reci");
				
				
				
				int zadatak = scanner.nextInt();
				
				scanner.nextLine();
				switch(zadatak) {
					case 1:
						
						System.out.println("Upisi novu rec");
				    	String nova_rec = scanner.nextLine();				    					 											
						writeInTextFile(nova_rec);
												
						break;
					
					case 2:
						
						String[] wordsAndTranslation = new String[3];
						String[] words = new String[3];
						String[] translation = new String[3];
						
						
						wordsAndTranslation = wordsToLearn();
						int i = 0;
						
						for (String temp : wordsAndTranslation) {
				            String[] parts = temp.split("-");
				            
				            if (parts.length == 2) {				            	
				            	words[i] = parts[0];
				            	translation[i] = parts[1];
				            	i++;
				            	
				            }
				        }
																											
						
						for(i = 0; i <=2; i++) {
							int wordLength = words[i].length();
							int missingChar = 0;
							
							if(wordLength <= 3)
								missingChar = 1;
							if(wordLength >= 4 && wordLength <= 6)
								missingChar = 2;
							if(wordLength >= 7 && wordLength <= 9)
								missingChar = 3;
							if(wordLength >= 10)
								missingChar = 4;
							
							//System.out.println("missing: " + missingChar);
							int[] position = new int[missingChar];
							
							for(int k = 0; k <= missingChar-1; k++) {																						
								Random random = new Random();
								int number;
								
								number = random.nextInt(wordLength-1);
								position[k] = number;
							}
							
							Arrays.sort(position);
							
							
							System.out.println();
							char[] charArray = words[i].toCharArray();
							        
							        
							for(int k = 0; k <= missingChar-1; k++) {															
								charArray[position[k]] = '_';
								
							}        
							 
							String myString = String.valueOf(charArray);
							System.out.print(myString + "-" + translation[i]);
							System.out.print("\n\n");
																				
							String corectAnswer = "";
							System.out.println("Upisi tacnu rec");
							corectAnswer = scanner.nextLine();
																					
							if(words[i].equalsIgnoreCase(corectAnswer)) {
								System.out.println("TACNO!");
							}else {
								System.out.println("NETACNO!");
							}
									
						}
																							
						break;
						
					default:
						System.out.println("Pogresan broj");
				
				}
				
				System.out.println("\n");
				System.out.println("Izlaz - 1 \nMenu - 0");
				kraj = scanner.nextInt();
				scanner.nextLine();
			
			}while (kraj != 1);
	    
			System.out.println("Aplikacija je izgasena");
			scanner.close();
	    }
	    
	    
	    
	    
	    public static int fileLength() {
	    	
	    	String relativePath = "src/textFile/reci.txt";	    				
    		Path absolutePath = Paths.get(System.getProperty("user.dir"), relativePath);
    		
    		int length=0;
            try (BufferedReader br = new BufferedReader(new FileReader(absolutePath.toFile()))) {              
                              	       
                while ((br.readLine()) != null) {
                	length++;	                    
                }
            } catch (IOException e) {
                e.printStackTrace();
				        
            }
	    	
            
	    	return length;
	    }
	    
	    
	    public static int[] wordsPosition() {
	    	
	    	int length = fileLength();
            
    		
            int[] position = new int[3];
            Random random = new Random();
        	
        	int minRange=0;
        	int number = random.nextInt(length-1);       
        	
        	position[0] = number;
            
            for(int j=1; j<=2; j++) {
            	number = random.nextInt(length-minRange);            	
        		            	
            	if(position[j-1] == number)
            	{
            		number++;
            		position[j] = number;
            	}else if(j==2 && position[j-2] == number) {
            		number++;
            		position[j] = number;
            	}  else {
            		position[j] = number;
            	}         	            	            
            }
            
            Arrays.sort(position);
                 
            return position;
	    	
	    }
	    
	    
	    
	    public static String[] wordsToLearn() {	    	
            String[] words = new String[3];
    		int[] position = wordsPosition();    		    		
    		
    		int i=0;
    		
    		String relativePath = "src/textFile/reci.txt";	    				
    		Path absolutePath = Paths.get(System.getProperty("user.dir"), relativePath);
    		
    		try (BufferedReader br = new BufferedReader(new FileReader(absolutePath.toFile()))) {
                String line;
                int currentLineNumber = 0;

                while ((line = br.readLine()) != null) {
                	
                    if (currentLineNumber == position[i]) {                   	
                    	words[i] = line;
                    	i++;           
                    	if(i == 3) {
                    		break;
                    	}
                        
                    }
                    currentLineNumber++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
           
    		
    		
			return words;
        
    }
	    
	    
	    

	    public static String readTextFile(int broj) {
	    		String relativePath = "src/textFile/reci.txt";	    				
	    		Path absolutePath = Paths.get(System.getProperty("user.dir"), relativePath);		

	            try (BufferedReader br = new BufferedReader(new FileReader(absolutePath.toFile()))) {
	                String line;
	                int i=0;	              	       
	                while ((line = br.readLine()) != null) {
	                    if(i == broj) {
	                    	//System.out.println(line);
	                    	return line;
	                    }
	                    i++;	                    
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
					return "Nema reci danas";	              
	            }
				return "Nema reci danas";
	        
	    }
	    
	    
	    public static void writeInTextFile(String rec) {
		    String relativePath = "src/textFile/reci.txt";
		    Path absolutePath = Paths.get(System.getProperty("user.dir"), relativePath);
		    
		    
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(absolutePath.toFile(), true))) {
	            writer.write(rec);
	            writer.newLine();
	            
	
	            System.out.println("Data has been written to the file successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
	  
	
	}
}
