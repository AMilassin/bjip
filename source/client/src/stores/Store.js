/*
 * Global singleton application store.
 * This contains all the information to display.
 *
 * @flow
 */
import { observable, autorun, action } from 'mobx';

import type TransportLayer from './TransportLayer';



export default class Store {

  // REST API
  transportLayer: TransportLayer;


  @observable projectUpdating: boolean = false;

  // current project we're working on
  @observable projectId: string;

  // project information (lead, leadEmail, ...)
  @observable projectInfo: Object;

  // project issue types
  @observable projectIssueTypes: Object;

  @observable projectIssueTypeSchemes: Map = new Map();



  constructor(transportLayer: TransportLayer) {
    this.transportLayer = transportLayer;
    this.registerActions();
  }

  registerActions() {
    // autorun is a MobX thing, it triggers when an internal variable is changed
    // in this case the change of projectId will trigger the execution of the callback fn.
    autorun(() => {
      if (this.projectId) {
        // update project information if projectId is changed
        this.updateProject();
      }
    });
  }


  @action setProject(projectId: string) {
    this.projectId = projectId;
  }

  getProjectIssueTypes(issueTypeId) {
    if (!this.projectIssueTypeSchemes) {
      return null;
    }
    return this.projectIssueTypeSchemes.get(issueTypeId);
  }



  updateProject() {
    this.projectUpdating = true;
    Promise.all([
      this.fetchProjectInfo(),
      this.fetchProjectIssueTypes(),
    ])
    .then(() => {
      this.projectUpdating = false;
    })
    .catch(() => {
      this.projectUpdating = false;
    });
  }

  fetchProjectInfo() {
    return this.transportLayer.fetchProjectInfo(this.projectId)
    .then((resp) => {
      this.projectInfo = resp;
    });
  }

  fetchProjectIssueTypes() {
    return this.transportLayer.fetchProjectIssueTypes(this.projectId)
    .then((resp) => {
      this.projectIssueTypes = resp;
      resp.forEach((issueType) => {
        this.fetchProjectIssueTypeScheme(issueType.id);
      });
    });
  }

  fetchProjectIssueTypeScheme(issueTypeId) {
    return this.transportLayer.fetchProjectIssueTypeScheme(this.projectId, issueTypeId)
    .then((resp) => {
      this.projectIssueTypeSchemes.set(issueTypeId, resp);
    });
  }

}
