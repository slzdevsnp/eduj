## create cluster stand on your pc
### goal
* install several identical linux guest vms in vmware
* configure their network properties so that they can be used to create a cluster 
### prereqs
   a computer with at  number of virtual cpus > then number of guest vms to be installed.  min 8 GB Ram,  initially 20 GB of disk space. This can grow to 60 - 100 GB if you let running vms with data load for very long periods of time.
### install in vmware player
  * free for personal use
  * https://customerconnect.vmware.com/de/downloads/info/slug/desktop_end_user_computing/vmware_workstation_player/16_0 
  * 
### perform standard install of a first guest vm
* Download  ubuntuserver iso  from   https://ubuntu.com/download/server
  * This iso is smaller, disk partitions are differents when with ubuntu desktop,  it is installed without X server and  gdm
* install a new vm using this iso  in vmware player 
   * choose disk max size 20 GB (default)
   * follow screen steps in a standard installation until you can login
     * during installation choose hostName as `node-0`
     * afer install is finished shutDown the vm
*  in Vmware go to vm  settings and  1 Vcpu, 4 GB Ram, network : NAT
*  how to check which vmnet is used for NAT
   *   on windows in vmware (workstation, player):
   *  stop your virtual machine
   *  go to virtual machine settings : Network adapter 
   *  change temporarily network connection from NAT to Custom
   *  check in a list of virtual network one used for NAT (usually VMnet8)
   *  swtich back network connection to NAT
   *  close virtual machine settings 
   *   on windows in powershell run `ipconfig`     
    * locate  IPV4 Adresse and Subnet Mask for Vmware Network Adapter used for NAT
### copy vm folder 2-4 times
On windows copy vm folder with disk   n times for a number of desired vms
 * add all vms in vmware by opening those folders
 * on each vm:
   * login into it
    * change hostname
    * change ip to static using config in /etc/netplan
    * install java
      
So  by the end of procedure you should have vms with names `node-0, node-1, node-2,..`   They shall have static ip adresses. In my case : `192.168.239.150 , 192.168.239.151, 192.168.239.152, ..`. 
#### install java
```sh
sudo apt update
java -version
sudo apt install openjdk-11-jre-headless -y
```
#### change host name
```
hostnamectl set-hostname node-1
```
ensure that `/etc/hosts` has a line
```txt
127.0.0.1 node-1
```

#### change ip to static on command line

This is tested on ubuntu servers running as vmware guess with network setting at NAT running on windows network VMnet8  192.168.239.1/24.

Modify network according to your machine 

On windows in powershell run `ipconfig`

Server VM folders  were copied. So their network name and ips should be changed to static 

```sh
#if not yet installed
sudo apt install net-tools

## in my case it is ens33
ifconfig
ip link

## see default gateway
ip route 
## in my case it is 19.168.239.2
```
change netplan config yaml file  in `/etc/netplan`

```sh
cd /etc/netplan
ls
sudo cp 00-installer-config.yaml 00-installer-config.yaml.bak
sudo vi 00-installer-config.yaml
```

contents of yaml config
```yaml
network:
  version: 2
  renderer: networkd
  ethernets:
    ens3:
      dhcp4: no
      addresses:
        - 192.168.239.150/24
      gateway4: 192.168.239.2
      nameservers:
          addresses: [8.8.8.8, 1.1.1.1]
```
run 
```sh
#apply changs
sudo netplan apply
#verify
ip addr show ens33
```
reboot the node machine to verify that network is changed


You need to ssh to your running vm  using windows putty,  windows 10 powersheell , windows terminal app ( free install from Microsoft store) because vmware console  does not support copy/paste

i.e. in my example from Windows powershelf,  or Windows terminal separate windows 

```sh
ssh yourUser@192.168.239.250
ssh yourUser@192.168.239.251
ssh yourUser@192.168.239.252
```

Paste and run snippet below to install kafka distribution in /opt directory  owned by user root.


```sh
kafka_dist="kafka_2.13-2.7.0"
kafka_version="2.7.0"
install_dir="/opt"
kafka_arch="${kafka_dist}.tgz"
sudo wget -O $install_dir/$kafka_arch https://archive.apache.org/dist/kafka/$kafka_version/$kafka_arch
cud=$(pwd)
cd $install_dir
sudo tar zxvf $kafka_arch
sudo ln -sf $kafka_dist kafka
cd $cud
```


For kafka cluster configurations you need to download reelevant zookper.conf and server.conf  from  slurm stand  and start zookeeper and  broker  on each node. 

on slurm standup node  those detail process and config files are found with command 
```sh
ps -ef | grep java
```

On slurm stand vms  zookeeper and kafka are configured as services using systemctl. 

//todo  configure zookeeper and kafka as services in systemctl. 

