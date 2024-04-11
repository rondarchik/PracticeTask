import './App.css';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import React from "react";
import Root from "./components/RootComponent";
import ListUsers from "./components/user/ListUsersComponent";
import AddUser from "./components/user/AddUserComponent";
import UpdateUser, { loader as UserLoader } from "./components/user/UpdateUserComponent";
import ListRoles from "./components/role/ListRolesComponent";
import AddRole from "./components/role/AddRoleComponent";
import UpdateRole, { loader as RoleLoader } from "./components/role/UpdateRoleComponent";
import ListRequestStatuses from "./components/ListRequestStatusesComponent";
import ListPayments from "./components/ListPaymentsComponent";
import ListCreditTypes from "./components/ListCreditTypesComponent";
import ListCreditRequests from "./components/ListCreditRequestsComponent";
import ListCredits from "./components/ListCreditsComponent";

function ListCredit() {
  return null;
}

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <App />,
    children: [
      {
        path: "/api/users",
        element: <ListUsers />
      },
      {
        path: "/api/users/update/:id",
        element: <UpdateUser />,
        loader: UserLoader
      },
      {
        path: "/api/users/add",
        element: <AddUser />
      },
      {
        path: "/api/roles",
        element: <ListRoles />
      },
      {
        path: "/api/roles/add",
        element: <AddRole />
      },
      {
        path: "/api/roles/update/:id",
        element: <UpdateRole />,
        loader: RoleLoader
      },
      {
        path: "/api/request_statuses",
        element: <ListRequestStatuses />
      },
      {
        path: "/api/payments",
        element: <ListPayments />
      },
      {
        path: "/api/credit_type",
        element: <ListCreditTypes />
      },
      {
        path: "/api/credit_requests",
        element: <ListCreditRequests />
      },
      {
        path: "/api/credits",
        element: <ListCredits />
      }
    ]
  }
]);

export default function App() {
    return (
      <RouterProvider router={router}>
      </RouterProvider>
  );
}

