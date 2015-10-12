<h1>Sample Clustering</h1>

<p>Here is a project aiming at clustering a set of samples composed of colored regions based on their similarity.<br/>
To tackle this problem, I decided to rely on the distance between samples since it is inversely correlated to similarity and
 most popular clustering approaches use this measure under the form of distance matrices.
 The program can be runned using the main method in the SampleClustering class as the entry point, where you fill find some useful comments and examples.</p>
 
<p><b>1.</b> My distance measure here is based on the analysis of the colors of each region using the HSV (Hue,Saturation.Value) model. This color model is 
known to be a more perceptually relevant than the usual RGB color model because it separates the hue information from the brightness.
Other models actually outperform the HSV model in that sense, but these require a higher complexity that is probably unnecessary for the study case presented here
(Just for information, I included the class ColorUtil that implements such a model, the L*a*b model, and that can be easily used as a replacement to our HSV-based approach).</p>

In this project, each Sample is divided into identical sub-blocks called SampleElement, and each SampleElement presents info relative to its featured color. 
The "color distance" between two SampleElements is the core of my distance measure: If considering the cartesian space created by the H,S and V components, 
the euclidean distance between the colors of two SampleElements will translate into their individual distance (For the sake of clarity, I approximated the 
differences found in the Hue channel, where colors with different Hue values are considered to be equidistant along the Hue axis).
Hence, the distance between two samples is the average distance between their respective SampleElements.
</p>
<p>
<b>2.</b> The implementation of the distance measure is found in the SampleElement class under the method called calcDistance. 
</p>
<p>
<b>3.</b> For the clustering step, I opted for the Neighbor Joining method, a popular approach in the field of phylogenetics. It is a bottom-up agglomerative hierarchical clustering method that requires a distance matrix as a starting point. Based on the pairwise distances of the samples, the algorithm recursively merges the two most similar or "neighboring" nodes(in this context the two most similar samples),
until the whole tree including all nodes is built.
Like most agglomerative clustering methods, The complexity of the NJ method is polynomial, but this is not problematic for such a small dataset. 
</p>
<p>
As an example, I wrote in the main method of the main class a piece of code that generates 4 samples with randomly generated SampleElements (and hence random colors).</p>
<br/>
The dataset, before the clustering, is naturally ordered:<br/>
sp1<br/>
sp2<br/>
sp3<br/>
sp4<br/>

Here is an example of output of the generated tree by the NJ method:<br/>
<br/>
******** TREE *******<br/>
root<br/>
___sp4<br/>
___sp2_sp1_sp3<br/>
______sp2<br/>
______sp1_sp3<br/>
_________sp1<br/>
_________sp3<br/>
<br/>
<p>In this case, the samples sp1 and sp3 are the most similar, and where merged into a node called sp1_sp3. This node is in turn more similar to sp2 than sp4.<br/>
Hence, if ordering them by similarity, the dataset looks like this:</P>
Sp1<br/>
Sp3<br/>
Sp2<br/>
Sp4<br/>



