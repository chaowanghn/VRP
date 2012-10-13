----------------------------------
TRUCK AND TRAILER ROUTING PROBLEM (TTRP)
----------------------------------

These test problems were introduced originally by I-Min Chao (2002) and have been reconstructed based on the results reporte by S. Scheuerer (2004) in his Ph.D. thesis.

The coordinates of the customers are the same as those of the classical Christofides, Mingozzi and Toth (CMT) instances (1979) CMT1, CMT2, CMT3, CMT4, CMT5 CMT11, CMT12

The name of the file follows the format: ttrpxx.dat where xx corresponds to the number given in Table 8 of Chao (2002). The corresponding best solution reported in Villegas et al. (2011) is given in file sol-ttrpxx.txt


The format of the data is as follows:


The first line contains the following information:

	Qt Qr n mt mr

where

	Qt = trucks capacity
	
	mt = number of available truck
	
	Qr = trailer capacity
	
	mr = number of available trailers
	
	n = number of customers


The next line contains, the information of the depot:

0	x 	y

where:

	0 = indicates that this line refers to the depot
	
	x = x coordinate
	
	y = y coordinate
	
Finally, the next n lines contain, for each customer, the following information:



	i x y q type 

where

	i = customer number

	x = x coordinate

	y = y coordinate

	q = customer demand 

	type = customer type (1 for truck customers, and 0 for vehicle customers)

	        



----------
REFERENCES
----------
I.-M. Chao. A tabu search method for the truck and trailer routing problem. Computers & Operations Research, 29:33-51,
2002.

N. Christofides, A. Mingozzi, and P. Toth. The vehicle routing problem. In N. Christofides, A. Mingozzi, P. Toth, and C. Sandi, editors, Combinatorial optimization, pages 315-338. Wiley, 1979.

S. Scheuerer. Neue Tabusuche-Heuristiken fr die logistische Tourenplanung bei restringierendem Anhngereinsatz, mehreren Depots und Planungsperioden. PhD thesis, School of Business, University of Regensburg, http://epub.uni-regensburg.de/10196/, 2004. [In German].

J. G. Villegas, C. Prins, C. Prodhon, A. L. Medaglia, and N. Velasco. A matheuristic for the truck and trailer routing problem. Working paper. Universidad de Antioquia/Universidad de los Andes / Université de technologie de Troyes, 2011.



