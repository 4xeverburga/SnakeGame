#include <iostream>
#include <iomanip>
#include <utility>
using std::setw;
using std::right;
using std::left;
using namespace std;  


//FUNCIONES RECURRENTES


//Funciones variables
template <typename T>
void insertar_elemento(T* array,int index,int end,T element);

template <typename T>
void print_arr(T* arr,int tamano_int);

template <typename T>
void print_2d_arr(T* arr,int i_int,int j_int);

template <typename T>
void fill_2d_arr(T* arr,int i_int,int j_int);

template <typename T>
void eliminar_elemento(T* array,int index,int cantidad_elementos);

template <typename T>
void ordenamiento_burbuja(T* array,int cantidad_elementos,bool menor_a_mayor);

void floyd_marshall(int* ady,int m);

bool binary_search_recursive(int* arr, int start, int end, int& search_n);

bool binary_search_iterative(int* arr, int start, int end, int& search_n);

int almacenar_palabras(string arr_a_llenar[], string& oracion );





int main()
{
   
	return 0;
}


//FUNCIONES

template <typename T>
void sort(node<T>*& referencia){
	
	sorted = NULL;
	node<T>* actual = referencia;

	while (actual != NULL){
		node<T>* siguiente = actual ->p_siguiente;
		sorted_insert(actual);	
		actual = siguiente;

	}
	referencia = sorted;
}

template <typename T>
void sorted_insert(node<T>*& nuevo){
	if (sorted == NULL || sorted ->contenido >= nuevo ->contenido){
		nuevo -> p_siguiente = sorted; 
		sorted = nuevo;
	}
	else {
		node<T>* actual = sorted;
		while (actual -> p_siguiente != NULL && actual -> p_siguiente -> contenido < nuevo -> contenido){
			actual = actual ->p_siguiente;
		}
		nuevo -> p_siguiente = actual -> p_siguiente;
		actual -> p_siguiente = nuevo;
	}
}


int almacenar_palabras(string arr_a_llenar[], string& oracion ){
	oracion=oracion+" ";
	string siguiente,actual,palabra="";
	int contador=0;

	for(int i=0;i<oracion.length()-1;i++){
		actual=oracion[i];
		siguiente=oracion[i+1];

		if(actual!=" "){
			palabra+=actual;
		}
		if(siguiente==" "){
			arr_a_llenar[contador]=palabra;
			if(palabra!=""){
				contador++;
			}
			palabra="";
		}	
	}

	//Regresa el numero de palabras
	return contador;
}


template <typename T>
void fill_2d_arr(T* arr,int i_int,int j_int){
	for (int i = 0; i < i_int; i++)
	{
		for (int j = 0; j < j_int; j++)
		{	
			cout<<"Ingrese el elemento "<<i<<"-"<<j<<" :"<<endl;
      	cin>>arr[j_int*i+j];
		}
		
	}
}

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

bool binary_search_recursive(int* arr, int start, int end, int& search_n){
	/*
	arr esta ordenado de mayor a menor
	*/
	int half=start+(end-start)/2;
	if(arr[half]==search_n){
		return true;
	}
	else
	{
		if(start==end){
			return false;
		}

		if(arr[half]>search_n){
			return binary_search_recursive(arr,half+1,end,search_n);
		}
		else
		{
			return binary_search_recursive(arr,start,half,search_n);
		}
	}
	
}

bool binary_search_iterative(int* arr, int start, int end, int& search_n){

	while(true){

		int half=start+(end-start)/2;
		if(arr[half]==search_n){
			return true;
		}
		else
		{
			if(start==end){
				return false;
			}

			if(arr[half]>search_n){
				start=half+1;
			}
			else
			{
				end=half;
			}

		}
	}
}

void floyd_marshall(int* ady,int m){
   for(int k=0;k<m;k++){
      for(int i=0;i<m;i++){
         for(int j=0;j<m;j++){
            int new_path=ady[m*i+k]+ady[m*k+j];
            if(new_path<ady[m*i+j]){
               ady[m*i+j]=new_path;
            }
         }
      }
   }
}


template <typename T>
void ordenamiento_burbuja(T* array,int cantidad_elementos,bool menor_a_mayor){
   if(menor_a_mayor){
      for(int i=0;i<cantidad_elementos-1;i++){
         for(int j=cantidad_elementos-1;j>i;j--){
            if(array[j-1]>array[j]){
               swap(array[j-1],array[j]);
            }
         }
      }
   }
   else{
      for(int i=0;i<cantidad_elementos-1;i++){
         for(int j=cantidad_elementos-1;j>i;j--){
            if(array[j-1]<array[j]){
        		swap(array[j-1],array[j]);
            }
         }
      }
   }
}


template <typename T>
void eliminar_elemento(T* array,int index,int cantidad_elementos){
   for(int i=index;i<cantidad_elementos-1;i++){
      array[i]=array[i+1];
   }
   //No olvidar restar 1 a "cantidad_elementos"
}

template <typename T>
void print_arr(T* arr,int tamano_int){
   cout<<"[ ";
   for(int i=0;i<tamano_int-1;i++){
      cout<<arr[i]<<", ";
   }
   cout<<arr[tamano_int-1];
   cout<<" ]"<<endl;

}

template <typename T>
void insertar_elemento(T* array,int index,int cantidad_elementos,T element){
   for(int i=cantidad_elementos-1;i>index;i--){
      array[i]=array[i-1];
   }
   array[index]=element;
}


