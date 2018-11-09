Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/xenial64"
  config.vm.box_check_update = false

  config.vm.provider "virtualbox" do |v|
    v.memory = 4096
  end

  config.vm.provision "dependencies", type: "shell", inline: <<-SHELL
    apt-get update
    apt-get install -y git openjdk-8-jdk-headless maven gcc
  SHELL

  config.vm.provision "environment", type: "shell", inline: <<-SHELL
    cat >/etc/environment <<'ENVIRONMENT'
GOPATH=/usr/local/gopath
PATH=/usr/local/gopath/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
ENVIRONMENT
  SHELL

  config.vm.provision "nomad", type: "shell", run: "always", inline: "source /etc/environment && export GOPATH && /vagrant/scripts/build-nomad.sh"
end
