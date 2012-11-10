
Truck And Trailer Routing Problem 
=================================

A collection of (meta)heuristic algorithms, aiming to solve the Truck and Trailer Routing Problem (TTRP)

The truck and trailer routing problem (TTRP) is a variant of the well known vehicle routing problem (VRP). 
Different from the VRP, in the TTRP, customers are serviced by a fleet of trucks and trailers. 
Due to some practical constraints, some customers can only be serviced by a single truck. 
The other customers can be serviced by a single truck or a truck pulling a trailer. 

The problem was proposed by [Chao,2002](http://140.113.119.114/students/thesis/096/2/Literature/VRP%20Variants/TTRP/2002_A%20TS%20method%20for%20the%20TTRP.pdf)

The standard benchmark instances used can be found [here](http://web.ntust.edu.tw/~vincent/ttrp/)
and are also available in the [proejct](http://github.com/Flooorents/ttrp/tree/master/src/test/resources/instances/benchmark)

For other research publications on the TTRP see [src/main/resources/papers](http://github.com/Flooorents/ttrp/tree/master/src/main/resources/papers):

License
-------

 Copyright [2012] [Florents Tselai <tselai@dmst.aueb.gr>]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

Repository Structure
--------------------
 * The project follows a standard maven structure