/*
 *
 * @flow
 */
import React from 'react';
import { observer, inject } from 'mobx-react';
import { observable } from 'mobx';

import type Store from '../stores/Store';

import ProjectInfo from '../smartcomponents/ProjectInfo';
import ProjectIssueTypes from '../smartcomponents/ProjectIssueTypes';



type Props = {
  store: Store,
  match: Object,
};


@inject('store')
@observer
export default class ProjectMain extends React.Component {

  props: Props;

  componentWillMount() {
    this.props.store.setProject(this.props.match.params.projectId);
  }


  render() {
    const projectId = this.props.match.params.projectId;
    if (this.props.store.projectUpdating) {
      return <div>Loading...</div>;
    }

    return (
      <div>
        <div>
          Project { projectId }
        </div>
        <ProjectInfo />
        <ProjectIssueTypes />
      </div>
    );
  }

}
