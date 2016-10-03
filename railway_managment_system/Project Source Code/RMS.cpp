//..................Header Files...................
#include<iostream>
#include<conio.h>
#include<fstream>
#include<iomanip>
#include<windows.h>
#include<dir.h>
#include<stdlib.h>
#include<string.h>
#define max 20
//..............Global Variable ....................
int num;
int i;
//................... namespace ..........
using namespace std;
//...........................Function Prototypes .............
void Empmenu();
void listemprec();
void Empmenu();
void Buildemp();
void nave(char);
void nave1();
void bf();
void rf();
void main_menu();
void admin_menu();
void customer_menu();
void gettrain();
void showtrain();
void reserve();
void getseat();
void showseats();
void groupmembers();
void start_search();
//.........................Directory Creator Function Definition ....................
void dircreator()
{
    char path[]="C:\Railway Managment System";
     mkdir(path);
}
void clearrec()
{
    ofstream clearfile("C:\\Railway Managment System\\TableData.txt",ios::trunc);
    clearfile.close();
    cout<<endl;
    nave1();
}

//..........................strut definitions ..........
struct employee
{
 char name[20];
 long int code;
 long int phone;
 char designation[20];
 int exp;
 int age;
};
struct detail
{
int trno;
char from[50];
char dest[50];
int c1,c1fare;
int c2,c2fare;
char time[10];
}det;

struct reserve
{
int trno;
char from[50];
char dest[50];
char pname[50][50];
int age[50];
char clas[50];
int nosr;
char date[15];
}res;

//.....................strut variables..............................
employee newemp[max],reademp[max];
//.....................Main function................................
            int main()
                {
                  system("cls");
                  system("COLOR f6");
                  int choise,n;
                  dircreator();
                  main_menu();
                  cin>>choise;

                  switch(choise)
                  {
                    case 1:  admin_menu();
                    break;
                    case 2:  customer_menu();
                    break;
                    case 3:  groupmembers();
                    break;
                    case 4:  exit(0);
                    default :
                    cout<<"\n\t\t INVALID CHARACTER ENTERED ";
                    getch();
                    //system("cls");
                    main_menu();
                  }
                getch();
                return 0;
                }

void admin_menu()  //administration menu definition
{
    int num;
    system("cls");
cout<<"\n\n\t\t************************************\n\n\n";
cout<<"\t\t\t[ 1 ]. ADD A NEW TRAIN       \n\n\t\t\t";
cout<<"[ 2 ]. EMPLOYEE MANAGEMENT         \n\n\t\t\t";
cout<<"[ 3 ]. GO BACK                     \n\n\n";
cout<<"\t\t************************************\n\n\t\t";
cout<<"ENTER YOUR CHOICE HERE >>>>>>>>>>\t";
cin>>num;
    if(num==1)
    {
        system("cls");
     gettrain();
    }
    else if(num==2)
    {
        system("cls");
        Empmenu();
    }
    else if(num==3)
    {
        system("cls");
        main_menu();
    }
    else
    {
        cout<<" INVALID CHARACTER ENTERED ";
        getch();
        system("cls");
        admin_menu();
    }
}

void customer_menu()   //customer menu definition
{
int val;
system("cls");
cout<<"\n\n\t\t*******************************************\n\n\n";
cout<<"\t\t\t[ 1 ]. SHOW AVAILABLE TRAINS       \n\n\t\t\t";
cout<<"[ 2 ]. RESERVE SEAT                \n\n\t\t\t";
cout<<"[ 3 ]. GO BACK                     \n\n\n";
cout<<"\t\t********************************************\n\n\t\t";
cout<<"ENTER YOUR CHOICE HERE >>>>>>>>>>\t";
cin>>val;
    if(val==1)
    {
     system("cls");
     showtrain();
    }
    else if(val==2)
    {
        system("cls");
        reserve();
    }
    else if(val==3)
    {
        system("cls");
        main_menu();
    }
    else
    {
        cout<<" INVALID CHARACTER ENTERED ";
        getch();
        system("cls");
        customer_menu();
    }
}
void main_menu()   //main menu definition
{
  system("cls");
  cout<<"\n\n\n\n\n";
  cout<<"\t\t********* WELCOME TO THE RAILWAY MANAGEMENT SYSTEM *********\n\n\n"<<endl;
  cout<<"\t\t\t[ 1 ]. ENTER ADMINISTRATION ACCOUNT \n\n\t\t\t";
  cout<<"[ 2 ]. ENTER CUSTOMER SERVICE      \n\n\t\t\t";
  cout<<"[ 3 ]. Group Members               \n\n\t\t\t";
  cout<<"[ 4 ]. Exit                        \n\n\n";
  cout<<"\t\t\t*****************************\n\n"<<endl;
  cout<<"\n\t\t\tEnter your choice here : ";
}


void gettrain()    //To add new train
{
  char a;
    do
    {
    cout<<"\n\n\tEnter the details correctly\n\n";
    cout<<"\n\t\t\tTrain no : \t";
    cin>>det.trno;
    cout<<"\n\t\t\tBoarding point : \t";
    cin>>det.from;
    cout<<"\n\t\t\tDestination : \t";
    cin>>det.dest;
    cout<<"\n\t\t\tNo of seats in first class : ";
    cin>>det.c1;
    cout<<"\n\t\t\tFare per ticket for first class : ";
    cin>>det.c1fare;
    cout<<"\n\t\t\tNo of seats in second class : ";
    cin>>det.c2;
    cout<<"\n\t\t\tFare per ticket for second class : ";
    cin>>det.c2fare;
    cout<<"\n\t\t\tDepature time : ";
    cin>>det.time;

   ofstream savedet("C:\\Railway Managment System\\Detail.txt",ios::app);    //To save inputs to a file
       savedet<<det.trno<<"\t";
       savedet<<det.from<<"\t";
       savedet<<det.dest<<"\t";
       savedet<<det.c1<<"\t";
       savedet<<det.c1fare<<"\t";
       savedet<<det.c2<<"\t";
       savedet<<det.c2fare<<"\t";
       savedet<<det.time<<"\t";
       savedet<<endl;
       savedet.close();
     cout<<"DO you want to add another train? (y,n) \t";
     cin>>a;
     }
   while( a =='y');
   admin_menu();
}

void showtrain()  //To view trains
{
  char ch;
  ifstream showdet("C:\\Railway Managment System\\Detail.txt",ios::app);  //To read from a file
	if(!showdet)
	{
	cout<<"THERE IS NO DATA TO DISPLAY";
	getch();
	exit(0);
	}
	cout<<"\n\n\t\tTRAINS AVAILABLE \n\n\n";
	cout<<"Train No. From   To  1st class  fare   2nd class   fare   Depature time\n\n";
	while(!showdet.eof())
	{
	 showdet.get(ch);
	 cout<<ch;
	}
	showdet.close();
    getch();
    main();
}
void reserve()   //reservation menu definition
{
  int value;
  system("cls");
  cout<<"\t\t***************************************\n\n\n";
  cout<<"\t\t\t[ 1 ]. Reserve a new seat             \n\n\t\t\t";
  cout<<"[ 2 ]. Show previously reserved seats \n\n\t\t\t";
  cout<<"[ 3 ]. Back \n\n\t\t\t";
  cout<<"[ 4 ]. Exit \n\n\n";
  cout<<"****************************************\n\n";
  cout<<"Enter your choice here : \t";
  cin>>value;
    switch(value)
    {
     case 1: getseat();
     break;
     case 2: showseats();
     break;
     case 3: main_menu();
     break;
     case 4: exit(0);
     break;
     default :
        main();
    }
}

void getseat()      // To reserve seat
{
  system("cls");
  cout<<"\n\n\t\tENTER THE DETAILS CORRECTLY : \n\n";
  cout<<"\t\t\tTrain no : ";cin>>res.trno;
  cout<<"\n\t\t\tNo of seats required : ";
  cin>>res.nosr;
  for(i=0;i<res.nosr;i++)
   {
    cout<<"\n\t\t\tPassenger name : ";cin>>res.pname[i];
    cout<<"\n\t\t\tPassenger age  : ";cin>>res.age[i];
   }
  cout<<"\n\t\t\tEnter the class (first or second) :";
  cin>>res.clas;
  cout<<"\n\t\t\tDate of reservation : ";
  cin>>res.date;
  cout<<"\n\n\n\n\t\t............SEAT RESERVATION SUCESSFUL............\n";

  ofstream saveres("C:\\Railway Managment System\\Reserve.txt",ios::app);
       saveres<<res.trno<<"\t";
       for(i=0;i<res.nosr;i++)
       {
       saveres<<res.pname[i]<<"\t";
       saveres<<res.age[i]<<"\t";
       }
       saveres<<res.clas<<"\t";
       saveres<<res.date<<"\t";
       saveres<<endl;
     saveres.close();
     getch();
reserve();
}
void showseats()   //To show reserved seats
{
  char ch;
  ifstream showseat("C:\\Railway Managment System\\Reserve.txt",ios::app);
	if(!showseat)
	{
	cout<<"THERE IS NO DATA TO DISPLAY";
	getch();
	main();
    }
	cout<<"\n\nNo.  ";
	for(i=0;i<res.nosr;i++)
    {
        cout<<"Passenger";
        cout<<"  age";
    }
    cout<<" Class  Reserved Date\n\n";
	while(!showseat.eof())
	{
	 showseat.get(ch);
	 cout<<ch;
	}
	showseat.close();
    getch();
	main();
}
void Empmenu()   //Employee administration menu
{
    char choice;
    system("cls");
    cout<<"\n\n\n";
    cout<<"\t~~~~~~~~~~~ Welcome to The RailWay Employee Management Menu ~~~~~~~~~~~\n\n"<<endl;
    cout<<"\t\t\t\tPress ...........\n"<<endl;
    cout<<setw(65)<<" [ 1 ].Build   Employee Database   \n "<<endl;
    cout<<setw(65)<<" [ 2 ].List    Employee Database   \n"<<endl;
    cout<<setw(65)<<" [ 3 ].Search Record               \n"<<endl;
    cout<<setw(65)<<" [ 4 ].Backup  Employee Database   \n"<<endl;
    cout<<setw(65)<<" [ 5 ].Restore Backup   File       \n"<<endl;
    cout<<setw(65)<<" [ 6 ].Clear all Records           \n"<<endl;
    cout<<setw(65)<<" [ 7 ].Go Back to main menu        \n"<<endl;
    cout<<setw(65)<<" [ 8 ].Exit                        \n"<<endl;
    cout<<setw(60)<<"Your Choice -->    ";
    cin>>choice;
    nave(choice);
}
void nave(char choice)   //navigate
{
    if(choice=='1')
        Buildemp();
    else if(choice=='2')
        listemprec();
    else if(choice=='3')
        start_search();
    else if(choice=='4')
    {
        bf();
        nave1();
    }
    else if(choice=='5')
    {
         rf();
         nave1();
    }
    else if(choice=='6')
        clearrec();
    else if(choice=='7')
        main_menu();
    else if(choice=='8')
        exit(0);
    else
      Empmenu();
}
void Buildemp() //To add new employee information
{
 char date[20];
 system("cls");
 cout<<setw(50)<<"Build The Table"<<endl;
 //normvideo();
 cout<<setw(50)<<"Maximum number of entries for one time entry -----  >  20"<<endl;
 cout<<setw(50)<<"How many do you want       ----->  ";
 cin>>num;
 cout<<setw(50)<<"Enter The Following Items"<<endl;
 for(int i=0;i<num;i++)
 {
  cout<<setw(30)<<"Name  ";
  cin>>newemp[i].name;
  cout<<setw(30)<<"Code  ";
  cin>>newemp[i].code;
  cout<<setw(30)<<"Designation  ";
  cin>>newemp[i].designation;
  cout<<setw(30)<<"Years of Experience  ";
  cin>>newemp[i].exp;
  cout<<setw(30)<<"Age  ";
  cin>>newemp[i].age;
  cout<<setw(30)<<"Phone No  ";
  cin>>newemp[i].phone;
 }
    cout<<setw(50)<<"Saving file to the disk";
    for(int i=0;i<10;i++)
    {
     cout<<" . ";
     Sleep(100);
    }cout<<endl;
	ofstream writetofile("C:\\Railway Managment System\\TableData.txt",ios::app);
	for(int i=0;i<=num;i++)
	{
	    writetofile<<newemp[i].code<<"\t";
	    writetofile<<newemp[i].name<<"\t\t";
	    writetofile<<newemp[i].designation<<"\t\t";
	    writetofile<<newemp[i].exp<<"\t\t";
	    writetofile<<newemp[i].age<<"\t";
	    writetofile<<newemp[i].phone<<"\t"<<endl;
	}
cout<<setw(50)<<"Going to main menu";
Sleep(100);
Empmenu();
}
void listemprec()  //To show employee information
{   char character;
    cout<<setw(30)<<"\n\n\t\t````````````List The Table````````````````\n\n"<<endl;
    cout<<"Code\tName\t\tDesignation\tYears(exp)\tAge  Phone"<<endl;
    ifstream listrec;
    listrec.open("C:\\Railway Managment System\\TableData.txt");
    listrec.get(character);
    while(listrec)
    {
      cout<<character;
      listrec.get(character);
    }
    nave1();
}
void nave1()
{
    cout<<setw(70)<<"Press any key to go back to the employee menu "<<endl;
    getch();
    Empmenu();
}

void bf()  //To backup file
{
    char ch;
    ifstream currefile;
    ofstream backupfile;
    backupfile.open("C:\\Railway Managment System\\backup.txt",ios::app);
    currefile.open("C:\\Railway Managment System\\TableData.txt");
    currefile.get(ch);
    while(!currefile.eof())
    {
        backupfile.put(ch);
        currefile.get(ch);
    }
    currefile.close();
    backupfile.close();
    cout<<"\t\tBackup Complete "<<endl;
}
void rf()  //To restore file
{
    char ch;
    ifstream resfile;
    ofstream rfile;
    resfile.open("C:\\Railway Managment System\\backup.txt");
    rfile.open("C:\\Railway Managment System\\TableData.txt",ios::ate);
    resfile.get(ch);
    while(resfile)
    {
        rfile.put(ch);
        resfile.get(ch);
    }
    resfile.close();
    rfile.close();

    cout<<"\t\tRestore Complete "<<endl;
}
void groupmembers() //To show group information
{
    ofstream gmembers("C:\\Railway Managment System\\PoweredBy.txt",ios::out);
    gmembers<<"\n\n\n\t\t     Name                             ID       "<<endl;
    gmembers<<"\t\t      1.Kirubel Solomon         NSR/6880/07 "<<endl;
    gmembers<<"\t\t      2.Rabra   Hierpa          NSR/4570/07 "<<endl;
    gmembers<<"\t\t      3.Samuel  Dereje          NSR/0459/07 "<<endl;
    gmembers.close();
    ifstream readgmb("C:\\Railway Managment System\\PoweredBy.txt");
    char ch;
    while(readgmb)
    {
        readgmb.get(ch);
        cout<<ch;
        Sleep(40);
    }
    readgmb.close();
   cout<<"\t\t\tPress any key to go back "<<endl;
   getch();
      main_menu();
}
//To search
void start_search()
{
    system("cls");
    cout<<"\n \n \t*********************************************"<<endl;

    string entery;
    string line;
    int result;
    int check=0;


    cout<<"\n\n\t\t Enter a character to search?"<<endl;
    cout<<"\n\t\t ";
    cin>>entery;

    ifstream reademp("C:\\Railway Managment System\\TableData.txt",ios::in);
    if(reademp.is_open())
    {
      cout<<"\n\n\t*********************************";
      cout<<"\n\t Result for your search: ";
      cout<<"\n\t *******************************\n\n\n";
      cout<<"Code\tName\t\tDesignation\tYears(exp)\tAge  Phone"<<endl;
        while(!reademp.eof())
        {
           getline(reademp,line);

           if((result=line.find(entery,0))!=string::npos)
           {
           cout<<line<<endl;
           check++;
           }
        }

        if(check>0)
        {
           cout <<"\n\n\n\t\t\tPress any key to go back ";
           getch();
           Empmenu();
        }
        else
        {
            system("cls");
            cout <<"\n\n\n\t\t\tNo Results were found\n\n\n \t\t\tPress any key to go back";
            getch();
            Empmenu();

        }
    }
}
