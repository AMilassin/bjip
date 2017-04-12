/*
 *
 * @flow
 */
import React from 'react';
import { observer, inject } from 'mobx-react';



type Props = {};


@inject('store')
@observer
export default class ProjectIssueTypes extends React.Component {

  props: Props;


  render() {
    const projectIssueTypes = this.props.store.projectIssueTypes;

    return (
      <div>
        <h1>Issue Types</h1>
        <hr />
        <div>
          { projectIssueTypes && projectIssueTypes.map(issueType => (
            <div key={ issueType.id }>
              <IssueType issueType={ issueType } />
              <IssueTypeSchemes issueTypeId={ issueType.id } />>
            </div>
          ))}
        </div>
      </div>
    );
  }

}


const IssueType = ({ issueType }) => (
  <div>
    { issueType.id } - { issueType.name } - { issueType.description } - { issueType.subTask ? 'Sub-Task' : 'Standard' }
  </div>
);


const IssueTypeSchemes = ({ }) => {

  {
    this.props.store.getProjectIssueTypes(issueType.id).map(issueTypeScheme => (
      <div key={ issueTypeScheme.iidd }>
        <IssueTypeScheme issueTypeScheme={ issueTypeScheme } />
      </div>
    ))
  }
};


const IssueTypeScheme = ({ issueTypeScheme }) => (
  <div>
    Scheme: { issueTypeScheme.id } - { issueTypeScheme.name } - {issueTypeScheme.description }
  </div>
);
