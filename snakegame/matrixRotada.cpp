#include <iostream>
#include<utility>
using namespace std;

const int M=2,N=3;

template <typename T>
void print_2d_arr(T* arr,int i_int,int j_int){
	for (int i = 0; i < i_int; i++)
	{
		cout<<"[ ";

		for (int j = 0; j < j_int; j++)
		{
      	cout<<arr[j_int*i+j]<<" ";
		}
      
		cout<<"] "<<endl;
		
	}
		cout<<endl;
}

struct Coord
{
   int i,j,dimM,dimN;
};

void cambio_base(Coord& ij){
   int temp = ij.j;
   ij.j = ij.dimM-ij.i-1;
   ij.i = temp;
   swap(ij.dimM,ij.dimN);

} 

void tranf_arr(Coord* arrI){
   for(int i=0;i<M*N;i++){
      cambio_base(*(arrI+i));
   }
}

void print_rotated(int rotacion_n,int matrx[M][N]){

   Coord base = {0,0,3,2};

   for(int i=0;i<rotacion_n;i++){
      cambio_base(base);
   }
   cout<<base.dimM<<base.dimN<<endl;
   Coord arrI[base.dimN][base.dimM];
   for(int i=0;i<base.dimN;i++){
      for(int j=0;j<base.dimM;j++){
         arrI[i][j]={i,j,base.dimN,base.dimM};
      }
   }
   for(int i=0;i<rotacion_n;i++){
      tranf_arr(arrI[0]);
   }



   for(int i=0;i<base.dimN;i++){
      cout<<"[ ";
      for(int j=0;j<base.dimM;j++){

         int nb_i=arrI[i][j].i;
         int nb_j=arrI[i][j].j;
      	cout<<matrx[nb_i][nb_j]<<" ";
      }
		cout<<"] "<<endl;
   }
   cout<<endl;
}

int main(){
   int matrx[M][N]={
      3,5,9,
      7,4,2
   };

   print_2d_arr(matrx[0],2,3);
   print_rotated(1,matrx);
   print_rotated(2,matrx);
   print_rotated(3,matrx);



   
   
   return 0;
}