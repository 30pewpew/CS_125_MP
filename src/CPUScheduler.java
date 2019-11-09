import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Arrays;

public class CPUScheduler 
{
	public static void main (String[] args) throws NumberFormatException, IOException, InterruptedException

	{
		System.out.println("[CS 125 - CPU SCHEDULER]");
		System.out.println("By: Ethan Sy | 2016 - 00318");
		
		Scanner scan = new Scanner(System.in);
        
        char choice;
        
//        Item temp = new Item(1,0,0,0,0,0,0,0);
//        System.out.print("this is a new item" + temp.id);
//        
//        itemarray[0].id;
        
        do
        {
            System.out.println("[0] EXIT");
            System.out.println("[1] FCFS");
            System.out.println("[2] SJF");
            System.out.println("[3] NON PRE EMPTIVE PRIORITY");
            System.out.println("[4] PRE EMPTIVE PRIORITY");
            System.out.println("[5] SRTF");
            System.out.println("[6] ROUND ROBIN");
            choice = scan.next().charAt(0);
        }
        while(choice < '0' || choice > '6');
        
        System.out.println("\n");
        
        switch(choice)
        {
            case '0' : 
            	System.out.println("EXITING");
                break;
            
            case '1' : 
            	System.out.println("FCFS");
            	FCFS();
                break;
                
            case '2' : 
            	System.out.println("SJF");
            	SJF();
                break;
                
            case '3' : 
            	System.out.println("NON PRE EMPTIVE PRIORITY");
            	NonPreemptivePriority();
                break;
            
            case '4' : 
            	System.out.println("PRE EMPTIVE PRIORITY");
            	PreemptivePriority();
            	break;
                
            case '5' : 
            	System.out.println("SRTF");
            	SRTF();
                break;    
                
            case '6' : 
            	System.out.println("ROUND ROBIN");
            	RoundRobin();
                break;
                
            default:
            	System.out.println("Invalid Input");     
            	break;
        }
      
	}
	
	public static void ProgramFlow()
	{
		// Input how many processes.
		// BT AT P
		
		// OUTPUT
		// PROCESS BT AT P CT WT RT TAT
		
		
//			Scanner sc = new Scanner(System.in);
//			System.out.println ("Enter # of processes:");
//			int n = sc.nextInt();
//			int bt[] = new int[n];
//			int at[] = new int[n];
//			int pid[] = new int[n];
//			int p[] = new int [n];
//			int st[] = new int[n];
//			int ft[] = new int[n];
//			int wt[] = new int[n];
//			int ta[] = new int[n];
//			
//			int i,j,temp;
//		    int totwt = 0, totta=0;
//		    float awt = 0, ata = 0;
//		    int pn[][] = new int[n][n];
//		    int t[] = new int[n];
//	
//		    
//		    
//		    for(i = 0; i < n; i++)
//		    {
//		        System.out.println("Input process " + (i + 1) + "s arrival time");
//		        at[i] = sc.nextInt();
//		        System.out.println("Input process " + (i + 1) + "s burst time");
//		        bt[i] = sc.nextInt();
//		        System.out.println("Input process " + (i + 1) + "s priority");
//		        p[i] = sc.nextInt();
//		        pid[i] = i + 1;
//		    }
//		    
//		    for (i = 0; i < n; i++)
//		    {
//		        for (j = 0; j < n; j++)
//		        {
//		            if(p[i] < p[j])
//		            {
//		                temp = p[i];
//		                p[i] = p[j];
//		                p[j] = temp;
//		                temp = at[i];
//		                at[i] = at[j];
//		                at[j] = temp;
//		                temp = bt[i];
//		                bt[i]= bt[j];
//		                bt[j] = temp;
//		                
//		                t = pn[i];
//		                pn[i] = pn[j];
//		                pn[j] = t;
//	                }
//	        	}
//	        }
//			    
//		    for (i = 0; i < n; i++)
//		    {		 
//		        if (i == 0)
//			    {
//		            st[i] = at[i];
//		            wt[i] = st[i] - at[i];
//		            ft[i] = st[i] + bt[i];
//		            ta[i] = ft[i] - at[i];
//		        }
//		        else
//		        {
//		            st[i] = ft[i - 1];
//		            wt[i] = st[i] - at[i];
//		            ft[i] = st[i] + bt[i];
//		            ta[i] = ft[i] - at[i];
//		        }
//		        
//		        totwt += wt[i];
//		        totta += ta[i];
//		    }
//			    
//		    awt=(float)totwt/n;
//		    ata=(float)totta/n;
//		    System.out.println("Process \tAT\tBT \tPriority \tWT \tTAT");
//		    for (i = 0; i < n; i++)
//		    {
//		        System.out.println(pn[i] + "\t"  + at[i] + "\t" + bt[i] + "\t" + p[i] + "\t\t" + wt[i] + "\t" + ta[i]);
//		    }
//		    System.out.println("Average waiting time is:" + awt);
//		    System.out.println("Average turnaroundtime is:" + ata);
//		}
//	
		}
		
	public static void FCFS()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter # of processes: ");
		int n = sc.nextInt();
		int pid[] = new int[n];   // Process #s
		int ar[] = new int[n];     // Arrival times
		int bt[] = new int[n];     // Burst Times
		int ct[] = new int[n];     // Completion Times
		int ta[] = new int[n];     // Turn Around Time
		int wt[] = new int[n];     // Waiting Time
		int gantt[] = new int[n+1]; 
		gantt[0] = 0;
		int temp;
		float avgwt = 0, avgta = 0;
 
		for (int i = 0; i < n; i++)
		{
			System.out.println("Input process " + (i+1) + "s Arrival Time: ");
			ar[i] = sc.nextInt();
			System.out.println("Input process " + (i+1) + "s Burst Time: ");
			bt[i] = sc.nextInt();
			gantt[i+1] = bt[i];
			pid[i] = i+1;
		}
 
		// Sort arrival times
		for (int i = 0 ; i <n; i++)
		{
			for (int  j=0;  j < n-(i+1) ; j++)
			{
				if ( ar[j] > ar[j+1] )
				{
					temp = ar[j];
					ar[j] = ar[j+1];
					ar[j+1] = temp;
					temp = bt[j];
					bt[j] = bt[j+1];
					bt[j+1] = temp;
					temp = pid[j];
					pid[j] = pid[j+1];
					pid[j+1] = temp;
				}
			}
		}
		
		// Solving completion time
		for (int  i = 0 ; i < n; i++)
		{
			if (i == 0)
			{	
				ct[i] = ar[i] + bt[i];
			}
			else
			{
				if( ar[i] > ct[i-1])
				{
					ct[i] = ar[i] + bt[i];
				}
				else
					ct[i] = ct[i-1] + bt[i];
			}
			ta[i] = ct[i] - ar[i];          // turnaround time = completion time- arrival time
			wt[i] = ta[i] - bt[i];          // waiting time= turnaround time- burst time
			avgwt += wt[i] ;               // total waiting time
			avgta += ta[i] ;               // total turnaround time
		}
		System.out.println("");
		System.out.println("Process  AT  \tBT  \tCT \tRT \tTAT \tWT");
		for(int  i = 0 ; i< n;  i++)
		{
			System.out.println(pid[i] + "  \t " + ar[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t"+ ta[i] + "\t"  + wt[i] ) ;
		}
		sc.close();
		System.out.println("");
		System.out.print("Gantt Chart:");
		System.out.print("O");
		System.out.print(" -- ");
		for (int i = 0; i < n+1; i++)
		{
			if (i == n) 
			{
				//System.out.print(ta[i]);
			} 
			else 
			{
				System.out.print("P" + pid[i]);
				System.out.print(" -- ");
				System.out.print(ct[i]);
				System.out.print(" -- ");
			}
		}
		
		System.out.println("");
		System.out.println("AVG waiting time: "+ (avgwt/n));     // printing average waiting time.
		System.out.println("AVG turnaround time: "+(avgta/n));    // printing average turnaround time.
	}

	public static void SJF()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println ("Enter # of processes:");
		int n = sc.nextInt();
		int pid[] = new int[n];
		int at[] = new int[n]; // Arrival Time
		int bt[] = new int[n]; // Burst Time
		int ct[] = new int[n]; // Completion Time
		int ta[] = new int[n]; // Turn Around Time
		int wt[] = new int[n];  // Waiting TIme
		int f[] = new int[n];  // Finished or not
		int gantt[] = new int[n+1];
		int st = 0, tot = 0;
		float avgwt = 0, avgta = 0;
 
		for(int i=0; i<n; i++)
		{
			System.out.println ("Input process " + (i+1) + " arrival time:");
			at[i] = sc.nextInt();
			System.out.println ("Input process " + (i+1) + " burst time:");
			bt[i] = sc.nextInt();
			pid[i] = i+1;
			f[i] = 0;
		}
		
		boolean a = true;
		while(true)
		{
			int c=n, min=999;
			if (tot == n) 
				break;
			
			for (int i=0; i<n; i++)
			{
				if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
				{
					min=bt[i];
					c=i;
				}
			}
			
			if (c==n) 
				st++;
			else
			{
				ct[c] = st + bt[c];
				st += bt[c];
				ta[c] = ct[c] - at[c];
				wt[c] = ta[c] - bt[c];
				f[c] = 1;
				tot++;
			}
		}
		
		System.out.println("Process  AT  \tBT  \tCT \tRT \tTAT \tWT");
		
		for (int i=0; i<n; i++)
		{
			avgwt += wt[i];
			avgta += ta[i];
			System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+ta[i]+"\t"+ta[i]+"\t"+wt[i]);
		}
		
		int smallest = 232342;
		int smallestPair = 0;
		//int btValue
		
		for (int i = 0; i < at.length; i++) {
			if (smallest >= at[i]) {
				smallest = at[i];
				smallestPair = bt[i];
				bt[i] =0; 
			}
		}
		
		Arrays.sort(bt);
		
		// Trying to print d gantt chart
//		for (int i = 0; i < bt.length; i++)
//		{
//			System.out.print(bt[i]);
//		}
		
		int counter = smallest + smallestPair;
		gantt[0] = smallest;
		gantt[1] = smallest + smallestPair;
		
		for (int i = 2; i < gantt.length; i++) 
		{
			gantt[i] = counter + bt[i-1];
			counter = counter + bt[i-1];	
		}
		
		// Just cloned the completion time array then sorted it
		int[] ganttarray = ct.clone();	
		int temp;

		// Cloned process id array
		int[] processarray = pid.clone();
        int tempProcess;
		
        for (int i = 0; i < n; i++) 
        {
            for (int j = i + 1; j < n; j++) 
            {
                if (ganttarray[i] > ganttarray[j]) 
                {
                	// Sort Gant Array
                    temp = ganttarray[i];
                    ganttarray[i] = ganttarray[j];            
                    ganttarray[j] = temp;
                    
                    // Sort Process based on sort of CT or Gant Array
                    tempProcess = processarray[i];
                    processarray[i] = processarray[j];
                    processarray[j] = tempProcess;
                }
            }
        }

		
		System.out.print("Gantt Chart:");
		System.out.print(" 0 -- ");
		// Gantt is working	
		for (int i = 0; i < n+1; i++)
		{
			if (i == n) 
			{
				//System.out.print(ta[i]);
			} 
			else 
			{
				System.out.print("P" + processarray[i]);
				System.out.print(" -- ");
				System.out.print(ganttarray[i]);
				System.out.print(" -- ");
			}
		}
		
		System.out.println ("\naverage tat is "+ (float)(avgta/n));
		System.out.println ("average wt is "+ (float)(avgwt/n));
		sc.close();
	}

	public static void NonPreemptivePriority()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println ("Enter # of processes:");
		int n = sc.nextInt();
		int pid[] = new int[n];
		int at[] = new int[n]; // Arrival Time
		int bt[] = new int[n]; // Burst Time
		int ct[] = new int[n]; // Completion Time
		int ta[] = new int[n]; // Turn Around Time
		int wt[] = new int[n];  // Waiting TIme
		int f[] = new int[n];  // Finished or not
		int priority[] = new int[n]; // Priority Time
		int gantt[] = new int[n+1];
		int tot = 0;
		float avgwt = 0, avgta = 0;
		
		float totwt = 0;
		float totta = 0;
		float awt, ata;
		
		int ft[] = new int[n];
		int st[] = new int[n];
		int rt[] = new int[n];
		int sorted_priority[] = new int[n];
		int sorted_at[] = new int[n];
		int sorted_bt[] = new int[n];
		int sorted_pid[] = new int[n];
		int sorted_st[] = new int[n];
		int sorted_wt[] = new int[n];
		int sorted_ft[] = new int[n];
		int sorted_ta[] = new int[n];
	
		for(int i=0; i<n; i++)
		{
			System.out.println ("Input process " + (i+1) + " arrival time:");
			at[i] = sc.nextInt();
			sorted_at[i] = at[i];
			System.out.println ("Input process " + (i+1) + " burst time:");
			bt[i] = sc.nextInt();
			sorted_bt[i] = bt[i];
			System.out.println ("Input process " + (i+1) + " priority time:");
			priority[i] = sc.nextInt();
			sorted_priority[i] = priority[i];
			
			pid[i] = i + 1;
			sorted_pid[i] = i + 1;
			f[i] = 0;
		}
		
		for(int i = 0; i < n; i++)
		{
	        for(int j = 0; j < n; j++)
	        {
	            if(sorted_priority[i] < sorted_priority[j])
	            {
	                int temp = sorted_priority[i];
	                sorted_priority[i] = sorted_priority[j];
	                sorted_priority[j] = temp;
	                
	                temp = sorted_at[i];
	                sorted_at[i] = sorted_at[j];
	                sorted_at[j] = temp;
	                
	                temp = sorted_bt[i];
	                sorted_bt[i] = sorted_bt[j];
	                sorted_bt[j] = temp;
	                
	                temp = sorted_pid[i];
	                sorted_pid[i] = sorted_pid[j];
	                sorted_pid[j] = temp;
	            }
	        }
		}
		
		for (int i = 0; i < n; i++)
		{
			if (i == 0)
			{
				sorted_st[i] = sorted_at[i];
				sorted_wt[i] = sorted_st[i] - sorted_at[i];
				sorted_ft[i] = sorted_st[i] + sorted_bt[i];
				sorted_ta[i] = sorted_ft[i] - sorted_at[i];
			}
			
			else
			{
				sorted_st[i] = sorted_ft[i - 1];
				sorted_wt[i] = sorted_st[i] - sorted_at[i];
				sorted_ft[i] = sorted_st[i] + sorted_bt[i];
				sorted_ta[i] = sorted_ft[i] - sorted_at[i];
			}
			totwt += sorted_wt[i];
			totta += sorted_ta[i];
		}
		awt = (totwt/n);
		ata = (totta/n);
		
		for (int i = 0; i < n; i++)
		{
			for (int j = (i + 1); j < n; j++)
			{
				if (sorted_pid[i] > sorted_pid[j])
				{
					// Sort Process ID
					int temp = sorted_pid[i];
					sorted_pid[i] = sorted_pid[j];
					sorted_pid[j] = temp;
					
					// Sort AT
					temp = sorted_at[i];
					sorted_at[i] = sorted_at[j];
					sorted_at[j] = temp;
					
					// Sort BT
					temp = sorted_bt[i];
					sorted_bt[i] = sorted_bt[j];
					sorted_bt[j] = temp;
					
					// Sort TA
					temp = sorted_ta[i];
					sorted_ta[i] = sorted_ta[j];
					sorted_ta[j] = temp;
					
					// Sort WT
					temp = sorted_wt[i];
					sorted_wt[i] = sorted_wt[j];
					sorted_wt[j] = temp;
				}
			}
		}
		
		// Solving completion and response time
		for (int i = 0; i < n; i++)
		{
			ct[i] = sorted_at[i] + sorted_ta[i];
			rt[i] = ct[i] - sorted_bt[i];
		}
		
		System.out.println("Process  AT  \tBT  \tPriority \tCT \tTAT \tWT \tRT");
		
//		for (int i=0; i<n; i++)
//		{
//			System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+ta[i]+"\t"+ta[i]+"\t"+wt[i]);
//		}
		
		for (int i=0; i<n; i++)
		{
			System.out.println(
								sorted_pid[i] + "\t " + sorted_at[i] + "\t" + sorted_bt[i] + "\t" + priority[i] + "\t" + "\t" + ct[i] + "\t" 
								+ sorted_ta[i] + "\t" + sorted_wt[i] + "\t" + rt[i]
							);
		}
		
		System.out.println();
		
		
		
		int smallest = 232342;
		int smallestPair = 0;
		//int btValue
		
		for (int i = 0; i < at.length; i++) {
			if (smallest >= at[i]) {
				smallest = at[i];
				smallestPair = bt[i];
				bt[i] =0; 
			}
		}
		
		Arrays.sort(bt);
		

		
		int counter = smallest + smallestPair;
		gantt[0] = smallest;
		gantt[1] = smallest + smallestPair;
		
		for (int i = 2; i < gantt.length; i++) 
		{
			gantt[i] = counter + bt[i-1];
			counter = counter + bt[i-1];	
		}
		
		// Just cloned the completion time array then sorted it
		int[] ganttarray = ct.clone();	
		int temp;
	
		// Cloned process id array
		int[] processarray = pid.clone();
	    int tempProcess;
		
	    for (int i = 0; i < n; i++) 
	    {
	        for (int j = i + 1; j < n; j++) 
	        {
	            if (ganttarray[i] > ganttarray[j]) 
	            {
	            	// Sort Gant Array
	                temp = ganttarray[i];
	                ganttarray[i] = ganttarray[j];            
	                ganttarray[j] = temp;
	                
	                // Sort Process based on sort of CT or Gant Array
	                tempProcess = processarray[i];
	                processarray[i] = processarray[j];
	                processarray[j] = tempProcess;
	            }
	        }
	    }

	
		System.out.print("Gantt Chart:");
		System.out.print(" 0 -- ");
		// Gantt is working	
		for (int i = 0; i < n+1; i++)
		{
			if (i == n) 
			{
				//System.out.print(ta[i]);
			} 
			else 
			{
				System.out.print("P" + processarray[i]);
				System.out.print(" -- ");
				System.out.print(ganttarray[i]);
				System.out.print(" -- ");
			}
		}
		
		System.out.println();
		System.out.println("Average TAT is: "+ ata);
		System.out.println("Average WT is: "+ awt);
		sc.close();
	}
	
	public static void PreemptivePriority() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      int n;
	      System.out.println("Input # of Processes: ");
	       n = Integer.parseInt(br.readLine());
	       // AT - proc[][0]
	       // RT - proc[][1]
	       // WT - proc[][2]
	       // TT - proc[][3]
	       int proc[][] = new int[n + 1][5];
	       int bt[][] = new int[n + 1][5];
	       int ct[][] = new int[n + 1][5];
	       int ct_sorted[][] = new int[n + 1][5];
	       int priority[][] = new int [n + 1][5];
	       int priority_sorted[][] = new int [n + 1][5];
	       int pair[][] = new int[n + 1][n + 1];
	       
	       for(int i = 1; i <= n; i++)
	       {
	    	   System.out.println("Input Arrival Time for Process " + i + ": ");
	    	   proc[i][0] = Integer.parseInt(br.readLine());
	    	   System.out.println("Input Burst Time for Process " + i + ": ");
	    	   proc[i][1] = Integer.parseInt(br.readLine());
	    	   bt[i][1] = proc[i][1];
	    	   System.out.println("Input Priority Time for Process " + i + ": ");
	    	   priority[i][1] = Integer.parseInt(br.readLine());
	    	   priority_sorted[i][1] = priority[i][1];
	       }
	       
	       System.out.println();
	     
	       //Calculation of Total Time and Initialization of Time Chart array
	       int total_time = 0;
	       
	       for(int i = 1; i <= n; i++)
	       {
	    	   total_time += proc[i][1];
	       }
	       
	       int time_chart[] = new int[total_time];
	     
	       for(int i = 0; i < total_time; i++)
	       {
	    	   //Picking the shortest process that arrived
	    	   
		      int sel_proc = 0;
		      int min = 99999;
		      for(int j = 1; j <= n; j++)
		      {
		    	  if(proc[j][0] <= i) // Did process arrive?
		    	  {
		    		  if(proc[j][1] < min && proc[j][1] != 0)
		    		  {
		    			  min = proc[j][1];
		    			  sel_proc = j;
		    		  }
		    	  }
		      }
		      
	      //Assign selected process to current time in the Chart
	      time_chart[i] = sel_proc;
	      
	      //Decrement Remaining Time of selected process by 1 since it has been assigned the CPU for 1 unit of time
	      proc[sel_proc][1]--;
	      
	      //WT and TT Calculation
	      for(int j = 1; j <= n; j++)
	      {
	    	  if(proc[j][0] <= i)
	    	  {
	    		  if(proc[j][1] != 0)
	    		  {
	    			  //If process has arrived and it has not already completed execution its TT is incremented by 1
	    			  proc[j][3]++;
	    			  
	    			//If the process has not been currently assigned the CPU and has arrived its WT is incremented by 1
	    			  if (j != sel_proc)
	    				  proc[j][2]++;
	    			  
	    		  }
	    		  //This is a special case in which the process has been assigned CPU and has completed its execution
	    		  else if (j == sel_proc)
	    		  {
	    			  proc[j][3]++;
	    		  }
	    	  }
	      }
	      
	      //Printing the Time Chart
	      if(i != 0)
	      {
	       if(sel_proc != time_chart[i - 1])
	        //If the CPU has been assigned to a different Process we need to print the current value of time and the name of new process
	       {
	        System.out.print(" -- " + i + " -- P" + sel_proc);
	       }
	      }
	      else//If the current time is 0 i.e the printing has just started we need to print the name of the First selected Process
	       System.out.print(i + " -- P" + sel_proc);
	      if(i == total_time - 1)//All the process names have been printed now we have to print the time at which execution ends
	       System.out.print(" -- " + (i + 1));
	      ct[sel_proc][1] = i + 1;
	     }
	     // End of Gantt Chart
	     System.out.println();
	     System.out.println();
	     
	     // Sort priority_sorted array
	     for (int i = 1; i < n + 1; i++)
	     {
	    	 for (int j = (i + 1); j < n + 1; j++)
	    	 {
	    		 if (priority_sorted[i][1] > priority_sorted[j][1])
	    		 {
	    			 int temp = priority_sorted[i][1];
	    			 priority_sorted[i][1] = priority_sorted[j][1];
	    			 priority_sorted[j][1] = temp;
	    		 }
	    		 
	    		 
	    	 }
	     }
	     
	     //Put values in ct_sorted
	     for (int i = 1; i < n + 1; i++)
	     {
	    	 ct_sorted[i][1] = ct[i][1];
	     }
	     
	  // Sort ct_sorted array
	     for (int i = 1; i < n + 1; i++)
	     {
	    	 for (int j = (i + 1); j < n + 1; j++)
	    	 {
	    		 if (ct_sorted[i][1] > ct_sorted[j][1])
	    		 {
	    			 int temp = ct_sorted[i][1];
	    			 ct_sorted[i][1] = ct_sorted[j][1];
	    			 ct_sorted[j][1] = temp;
	    		 }
	    	 }
	     }
	     
	     // ORIGINAL PRIORITY
	     // 2 	6 	3 	5 	4 	10 	9
	     
	     // PRIORITY PAIR
	     // 2 	3 	4 	5 	6 	9 	10
	     // CT PAIR
	     // 1 	5 	10 	16 	22 	30 	45
	     
	     // ASSEMBLING PAIR
	     for (int i = 1; i < priority.length; i++)
	     {
	    	 for (int j = 1; j < priority.length; j++)
	    	 {
	    		 if (priority_sorted[i][1] == priority[j][1])
	    		 {
	    			 int temp;
	    			 temp = priority_sorted[i][1];
	    			 priority_sorted[i][1] = priority_sorted[j][1];
	    			 priority_sorted[j][1] = temp;
	    			 
	    			 temp = ct_sorted[i][1];
	    			 ct_sorted[i][1] = ct_sorted[j][1];
	    			 ct_sorted[j][1] = temp; 
	    		 }
	    	 }
	     }
	     
	     for (int i = 1; i < n + 1; i++)
	     {
	    	 for (int j = 1; j < n + 1; j++)
	    	 {
	    		 proc[i][3] = ct_sorted[i][1] - proc[i][0];
	    		 proc[i][2] = proc[i][3] - bt[i][1];
	    	 }
	     }
	     
	     //Printing the WT and TT for each Process
	     System.out.println("Process  AT \tBT \tPriority \tCT \tWT \tTAT");
	     for(int i = 1; i <= n; i++)
	     {
	    	 System.out.printf("%d\t%2d\t%2d\t%2d\t\t%2d\t%2d\t%2d",i,proc[i][0],bt[i][1],priority[i][1],ct_sorted[i][1],proc[i][2],proc[i][3]);
	    	 System.out.println();
	     }
	     
	     System.out.println();
	     
	     //Printing the average WT & TT
	     float WT = 0,TT = 0;
	     for(int i = 1; i <= n; i++)
	     {
	      WT += proc[i][2];
	      TT += proc[i][3];
	     }
	     WT /= n;
	     TT /= n;
	     System.out.println("The Average WT is: " + WT);
	     System.out.println("The Average TAT is: " + TT);
	}
	
	public static void SRTF() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      int n;
	      System.out.println("Input # of Processes: ");
	       n = Integer.parseInt(br.readLine());
	       // AT - proc[][0]
	       // RT - proc[][1]
	       // WT - proc[][2]
	       // TT - proc[][3]
	       int proc[][] = new int[n + 1][4];
	       int bt[][] = new int[n + 1][4];
	       int ct[][] = new int[n + 1][4];
	       
	       for(int i = 1; i <= n; i++)
	       {
	    	   System.out.println("Input Arrival Time for Process " + i + ": ");
	    	   proc[i][0] = Integer.parseInt(br.readLine());
	    	   System.out.println("Input Burst Time for Process " + i + ": ");
	    	   proc[i][1] = Integer.parseInt(br.readLine());
	    	   bt[i][1] = proc[i][1];
	       }
	       
	       System.out.println();
	     
	       //Calculation of Total Time and Initialization of Time Chart array
	       int total_time = 0;
	       
	       for(int i = 1; i <= n; i++)
	       {
	    	   total_time += proc[i][1];
	       }
	       
	       int time_chart[] = new int[total_time];
	     
	       for(int i = 0; i < total_time; i++)
	       {
	    	   //Picking the shortest process that arrived
	    	   
		      int sel_proc = 0;
		      int min = 99999;
		      for(int j = 1; j <= n; j++)
		      {
		    	  if(proc[j][0] <= i) // Did process arrive?
		    	  {
		    		  if(proc[j][1] < min && proc[j][1] != 0)
		    		  {
		    			  min = proc[j][1];
		    			  sel_proc = j;
		    		  }
		    	  }
		      }
	      
	      //Assign selected process to current time in the Chart
	      time_chart[i] = sel_proc;
	      
	      //Decrement Remaining Time of selected process by 1 since it has been assigned the CPU for 1 unit of time
	      proc[sel_proc][1]--;
	      
	      //WT and TT Calculation
	      for(int j = 1; j <= n; j++)
	      {
	    	  if(proc[j][0] <= i)
	    	  {
	    		  if(proc[j][1] != 0)
	    		  {
	    			  //If process has arrived and it has not already completed execution its TT is incremented by 1
	    			  proc[j][3]++;
	    			  
	    			//If the process has not been currently assigned the CPU and has arrived its WT is incremented by 1
	    			  if (j != sel_proc)
	    				  proc[j][2]++;
	    			  
	    		  }
	    		  //This is a special case in which the process has been assigned CPU and has completed its execution
	    		  else if (j == sel_proc)
	    		  {
	    			  proc[j][3]++;
	    		  }
	    	  }
	      }
	      
	      //Printing the Time Chart
	      if(i != 0)
	      {
	       if(sel_proc != time_chart[i - 1])
	        //If the CPU has been assigned to a different Process we need to print the current value of time and the name of new process
	       {
	        System.out.print(" -- " + i + " -- P" + sel_proc);
	       }
	      }
	      else//If the current time is 0 i.e the printing has just started we need to print the name of the First selected Process
	       System.out.print(i + " -- P" + sel_proc);
	      if(i == total_time - 1)//All the process names have been printed now we have to print the time at which execution ends
	       System.out.print(" -- " + (i + 1));
	      ct[sel_proc][1] = i + 1;
	      
	      
	      
	     }
	     System.out.println();
	     System.out.println();
	     
	     //Printing the WT and TT for each Process
	     System.out.println("Process  AT \tBT \tCT \tWT \tTAT");
	     for(int i = 1; i <= n; i++)
	     {
	    	 System.out.printf("%d\t%2d\t%2d\t%2d\t%2d\t%2d",i,proc[i][0],bt[i][1],ct[i][1],proc[i][2],proc[i][3]);
	    	 System.out.println();
	     }
	     
	     System.out.println();
	     
	     //Printing the average WT & TT
	     float WT = 0,TT = 0;
	     for(int i = 1; i <= n; i++)
	     {
	      WT += proc[i][2];
	      TT += proc[i][3];
	     }
	     WT /= n;
	     TT /= n;
	     System.out.println("The Average WT is: " + WT);
	     System.out.println("The Average TAT is: " + TT);
	}
	
	public static void RoundRobin() throws NumberFormatException, IOException
	{
	      int k,q,n,ts,temp;
	      int aw;
	      float awt;
	      int j = 0;
	      
	      System.out.println("Enter # of processes");
	      Scanner sc = new Scanner(System.in);
	      n = sc.nextInt();
	      
	      int bt[] = new int[n];
	      int wt[] = new int[n];
	      int te[] = new int[n];
	      int rt[] = new int[n];
	      int at[] = new int[n];
	      int ct[] = new int[n];
	      int tat[] = new int[n];
	      
	      for (int i = 0; i < n; i++)
			{
				System.out.println ("Input process " + (i+1) + " burst time:");
				bt[i] = sc.nextInt();
				rt[i] = bt[i];
				System.out.println ("Input process " + (i+1) + " arrival time:");
				at[i] = sc.nextInt();
				te[i] = 0;
				wt[i] = 0;
			}
			
	      // Sort according to arrival time
	      for(int i=0;i<n;i++)
	       {
	         for(j=i+1;j<n;j++)
	          {
	              if(at[i]>at[j])
	                {
	                     temp=at[i];                                          //sorting according to arrival time
	                     at[i]=at[j];
	                     at[j]=temp;
	                     temp=bt[i];
	                     bt[i]=bt[j];
	                     bt[j]=temp;
	               }
	          }
	      }
	      
	      System.out.println("Enter Time Quantum: ");
	      ts = sc.nextInt();
	      q = 0;
	      awt = 0;

	     System.out.println("Gannt chart:");
	     j=0;

	     while(j <= n)
	     {
	    	 j++;
	    	 for(int i = 0; i < n; i++)
		     {
	    		 if(rt[i] == 0)
    			 {
	    			 continue;
    			 }
		            
	    		 if(rt[i] > ts)
	            {
	            	System.out.print(q + " \t " + "P" + (i + 1) + " \t ");
	            	//System.out.print("\n %d\t P%d" + q + (i+1));
	                 q = q + ts;
	                 rt[i] = rt[i] - ts;
	                 te[i]= te[i] + 1;
	            }
		            
	            else
	            {
	            	System.out.print(q + " \t " + "P" + (i + 1) + " \t ");
	            	//System.out.print("\n %d\t P%d" + q + (i+1));
	                wt[i] = q - te[i] * ts;
	                q = q + rt[i]; 
	                rt[i] = rt[i] - rt[i];
	                
		    		if ((i + 1) == n)
		    		{
		    			System.out.print(q);
		    		}
	            }
		     }
    	 }
	     
	     for (int i = 0; i < n; i++)
	     {
            wt[i]=wt[i]-at[i];
            awt=awt+wt[i];
            rt[i] = bt[i];
         }
	     
	     int totalTAT = 0;
	     for (int i = 0; i < n; i++)
	     {
	    	 ct[i] = at[i] + bt[i] + wt[i];
	    	 tat[i] = bt[i] + wt[i];
	    	 totalTAT = totalTAT + tat[i];
	     }
	     
	     System.out.println();
	     System.out.println();

		 System.out.println("Process AT \tBT \tCT \tRT \tTAT \tWT");
		 for (int i = 0; i < n; i++)
		 {
			 System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + rt[i] + "\t" + tat[i] + "\t" + wt[i]);
		 }
	     	     
	     aw=(int) awt;
	     System.out.println("Avg turn around time: " + (totalTAT / n));
	     System.out.println("Avg waiting time: " + (awt/n));
	}
}