# Cores

Cloud Computer companies (cloud computing) such as Amazon, have a set of physical machines (servers) in
which they create virtual machines (VMs) that they provide to users versus specific remuneration. 
This allows them to use a physical machine to create many more virtual machines which share
the resources of the physical machine in which they were created, increasing the profits of the company. 

In addition, in some forms of VMs, such as spot instances, each client proposes
his own special offer for the price he will have to pay for renting VMs

### OPERATION A
The provider, in order to reduce the management burden, wants to have the smallest possible number
VMs per customer. Therefore, the minimum number of VMs per client is required to be found,
assuming the provider can set up VMs with only 1, 2, 7 or 11 cores. The
program should print the minimum number per client on the screen.

### OERATION B
The provider does not have enough physical cores to satisfy all customers while
it is forbidden for many VMs to share the same physical kernel. Based on those available
kernels, it has been asked to find the maximum payment amount that can be obtained by selecting
to eligible customers. The program should print this maximum amount on the screen
