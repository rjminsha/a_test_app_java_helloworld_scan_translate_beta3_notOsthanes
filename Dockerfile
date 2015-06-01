#  Copyright 2015 IBM
#
#   Licensed under the Apache License, Version 2.0 (the "License");
#   you may not use this file except in compliance with the License.
#   You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an "AS IS" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.

FROM registry-ice.ng.bluemix.net/ibmliberty
#FROM ibmliberty
MAINTAINER Nathan Fritze "nfritz@us.ibm.com"

# create the server
RUN bash -c "server create wordcounter"
# Install the application
ENV WEB_PORT 9080
EXPOSE  9080
ADD server.xml /opt/ibm/wlp/usr/servers/wordcounter/server.xml
ADD wordcounter.war /opt/ibm/wlp/usr/servers/wordcounter/apps/wordcounter.war

# Define command to run the application when the container starts
ENTRYPOINT ["/bin/bash", "-c", "server run wordcounter"]

