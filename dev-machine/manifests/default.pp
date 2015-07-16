class busdevops::devmachine::installation {
  class{ 'busdevops::devmachine::installation::packages': } ->
  class{ 'busdevops::devmachine::installation::dbdir': } ->
  class{ 'busdevops::devmachine::installation::dockercompose': }

}

class busdevops::devmachine::installation::dbdir {
  file { [ "/var/lib/mysql", "/var/lib/mysql/demo-site"]:
    ensure => "directory",
  }
}

class busdevops::devmachine::installation::packages {
  package{ ['curl']:
    ensure => 'installed'
  }
}

class busdevops::devmachine::installation::dockercompose {
  exec { 'download docker compose':
    command => "curl -L https://github.com/docker/compose/releases/download/1.3.2/docker-compose-Linux-x86_64 > /usr/local/bin/docker-compose",
    path    => ["/usr/bin", "/usr/sbin"],
    onlyif  => "test ! -f /usr/local/bin/docker-compose"
  } ->
  exec { 'Add execution rights to docker compose':
    command => "chmod +x /usr/local/bin/docker-compose",
    path    => ["/bin", "/sbin"]
  } ->
  service { "docker":
    ensure  => "running",
    enable  => "true"
  }
}

node default {
  class { 'busdevops::devmachine::installation' : }
}