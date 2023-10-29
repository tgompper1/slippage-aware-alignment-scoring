# slippage-aware-alignment-scoring
### Sequence Alignment Slippage-Aware Scoring
Insertions and deletions are often caused by DNA polymerase slippage, which results in the 
duplication of one or more nucleotides, or the deletion of one or more copies of a repeated 
nucleotide. This is an algorithm to solve a modified version global pairwise alignment that 
captures this notion.\
\
<ins>Slippage-aware alignement scheme:</ins>\
Given:
- Sequences S and T aligned in an alignment A
- Substitution cost matrix M
- Slippage gap penalty cs
- Non-slippage gap penalty cn (generally we would have cn > cs )\
  
Score(A) = same as standard linear gap penalty scoring scheme, except that 
1. if A[1,i] = ‘-‘, the score assigned to column i is:\
cs if A[2,i]=A[2,i-1]\
cn otherwise
2. if A[2,i] = ‘-‘, then case assigned to column i is:\
cs if A[1,i]=A[1,i-1]\
cn otherwise

Examples:\
M = +1/-1 for matches/mismatches, cs = -1, cn = -2\
1. AAAG\
   A--T\
   Score: 1(-1)(-1)(-1)=-2
2. AACG\
   A--T\
   Score: 1(-1)(-2)(-1)=-3
3. AT---GA\
   ACCGGGA\
   Score: 1(-1)(-1)(-2)(-1)11=-2
4. ATTA-GT\
   AT-AAG-\
   Score: 11(-1)1(-1)1(-2)=0

 
