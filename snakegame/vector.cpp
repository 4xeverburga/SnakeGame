#include <vector>
#include <iostream>

int main(){
   std::vector<const int> v_ej = {1,2,3};
   // [ const int, const int ]

   int n = 1;
   v_ej.push_back(n);
   // v_ej[3]=2;
   std::cout<<v_ej[1]<<std::endl;
   return 0;
}