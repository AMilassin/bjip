/*
 *
 * @flow
 */
import { BrowserRouter } from 'react-router-dom';
import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'mobx-react';

import App from './app';
import { store } from './stores';


/*
 * Initialize React component.
 * The Provider enables store delegation to the components.
 * The BrowserRouter is the root react-router-v4 component.
 * The basename stores the root url for the client side application.
 */
const main = (
  <Provider store={ store } >
    <BrowserRouter basename={ __glassAppGlobalRoute__ }>
      <App />
    </BrowserRouter>
  </Provider>
);

const root = document.getElementById('glass_app_root');
ReactDOM.render(main, root);
