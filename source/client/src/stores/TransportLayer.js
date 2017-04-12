/*
 *
 * Transport layer.
 * Client REST API communicating with the server side REST API.
 *
 * Based on fetch API.
 *
 * @flow
 */


// API ROOT kiolvasása a template-ben beállított globális változóból
const API_ROOT = __glassAppRestRoute__;

const DEFAULT_HEADER = {
  method: 'get',
  credentials: 'include',
};


export default class TransportLayer {

  fetchProjectInfo(projectId: string) {
    return fetch(`${API_ROOT}/project/${projectId}`, DEFAULT_HEADER)
        .then(resp => resp.json());
  }

  fetchProjectIssueTypes(projectId: string) {
    return fetch(`${API_ROOT}/project/${projectId}/issuetypes`, DEFAULT_HEADER)
        .then(resp => resp.json());
  }

  fetchProjectIssueTypeScheme(projectId: string, issueTypeId: string) {
    return fetch(`${API_ROOT}/project/${projectId}/issuetypes/${issueTypeId}/issuetypeschemes`, DEFAULT_HEADER)
        .then(resp => resp.json());
  }

}
