# SFTP Connection Pool

## Overview

The SFTP Connection Pool project is designed to manage multiple connections to an SFTP server efficiently. It provides a pool of connections that can be reused, which helps improve performance and resource management on the client side.

## Features

- Connection pooling to reduce overhead
- Automated connection management
- Simple API for connecting to SFTP servers
- Supports multiple simultaneous connections

## Installation

To install the SFTP Connection Pool, use the following command:

```bash
pip install sftp-connection-pool
```

## Usage

Here’s an example of how to use the SFTP Connection Pool:

```python
from sftp_connection_pool import SFTPConnectionPool

# Create a connection pool
pool = SFTPConnectionPool(host='sftp.example.com', username='user', password='pass')

# Get a connection from the pool
with pool.get_connection() as sftp:
    # Use the SFTP connection
    sftp.put('local_file.txt', 'remote_file.txt')
```

## Contributing

If you would like to contribute to this project, please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.