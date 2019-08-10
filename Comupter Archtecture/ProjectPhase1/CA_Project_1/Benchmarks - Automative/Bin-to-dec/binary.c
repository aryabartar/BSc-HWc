#include<stdio.h>    
#include<stdlib.h>  
#include<time.h>
int main(){  
int a[10],n,i;  
clock_t start,end1;
end1 = clock();  
start = clock(); 
system ("cls");  
printf("Enter the number to convert: ");    
n = 120;
for(i=0;n>0;i++)    
{    
a[i]=n%2;    
n=n/2;    
}    
start = clock() - start;
printf("\n%f numbers of clocks--- ---------------------",(double)(start) / CLOCKS_PER_SEC);
printf("\nBinary of Given Number is=");    
   
end1 = clock() - end1;
printf("\n%f all-----------------------",(double)(end1) / CLOCKS_PER_SEC);
return 0;  
}
