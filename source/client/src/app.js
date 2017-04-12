/*
 *
 * @flow
 */
import { Route, Switch } from 'react-router-dom';
import React from 'react';
import DevTools from 'mobx-react-devtools';

import ProjectMain from './containers/ProjectMain';



const NoMatch = ({ location }) => (
  <div>
    Unknown path: { location.pathname }
  </div>
);


export default class App extends React.Component {

  render() {
    return (
      <div>
        <h1>GLaSS</h1>

        <Switch>
          <Route path="/:projectId" component={ ProjectMain } />
          <Route component={ NoMatch } />
        </Switch>

        { __DEV__ && <DevTools /> }
      </div>
    );
  }

}
