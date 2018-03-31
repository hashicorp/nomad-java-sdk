Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.provider "virtualbox" do |v|
    v.memory = 4096
  end

  config.vm.provision "dependencies", type: "shell", inline: <<-SHELL
    apt-get install -y git openjdk-7-jdk-headless maven
  SHELL

  config.vm.provision "nomad", type: "shell", run: "always", inline: "/vagrant/scripts/build-nomad.sh"
end
