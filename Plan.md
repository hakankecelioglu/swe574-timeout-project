Project plan with tasks, their starting and ending dates and also assigning resources is as follows.

# Project Plan #
| **Task Name** | **Duration** | **Start** | **Finish** | **Predecessors** | **Resource Names**|
|:--------------|:-------------|:----------|:-----------|:-----------------|:------------------|
| RSD version 1.0 | 15 days      | Mon 16.02.2015 | Tue 03.03.2015 | -                | Munevver, Morteza, Oğuz, <br /> Sezgi, Tuncay |
| Development Environment Installation | 21 days      | Mon 16.02.2015 | Mon 09.03.2015 | -                | Hakan, Oğuz, Haluk|
| RSD version 2.0 | 7 days       | Mon 02.03.2015 | Mon 09.03.2015 | RSD version 1.0  | Münevver, Sezgi, Hakan, <br /> Oğuz, Tuncay, Haluk, <br /> Morteza|
| DSD (High Level) version 1.0 | 7 days       | Mon 09.03.2015 | Mon 16.03.2015 | RSD version 2.0  | Münevver, Sezgi, Hakan, <br /> Oğuz, Tuncay, Haluk, <br /> Morteza|
| DSD (Low Level) version 1.1 | 7 days       | Mon 16.03.2015 | **Mon 13.04.2015** | DSD (High Level) version 1.0 | Münevver, Sezgi, Hakan, <br /> Oğuz, Tuncay, Haluk, <br /> Morteza|
| Implementation - Backend | 35 days      | Mon 23.03.2015 | Mon 27.04.2015 | DSD (Low Level) version 1.1 |  Tuncay, Hakan, Haluk, <br /> Oğuz|
| Implementation - Database | 35 days      | Mon 23.03.2015 | Mon 27.04.2015 | DSD (Low Level) version 1.1 | Hakan, Tuncay     |
| Implementation - Frontend | 35 days      | Mon 23.03.2015 | Mon 27.04.2015 | DSD (Low Level) version 1.1 | Munevver, Oğuz, Morteza,<br /> Sezgi |
| Implementation - Web Services | 56 days      | Mon 23.03.2015 | Mon 27.04.2015 | DSD (Low Level) version 1.1 | Tuncay, Hakan, Haluk, <br /> Oğuz, Münevver|
| Implementation - Android | 35 days      | Mon 23.03.2015 | Mon 27.04.2015 | DSD (Low Level) version 1.1 | Haluk, Sezgi      |
| Implementation - Semantic Tagging | 21 days      | Mon 30.03.2015 | Mon 20.04.2015 | DSD (Low Level) version 1.1 | Tuncay, Munevver, Sezgi|
| Product Demo 1 | 0 day        | Mon 30.03.2015 | Mon 30.03.2015| Partial Completion of implementation | Münevver, Sezgi, Hakan, <br /> Oğuz, Tuncay, Haluk, <br /> Morteza|
| Product Demo 2 | 0 day        | Mon 27.04.2015 | Mon 27.04.2015| Completion of Implementation | Münevver, Sezgi, Hakan, <br /> Oğuz, Tuncay, Haluk, <br /> Morteza|
| Testing       | 49 days      | Mon 30.03.2015 | Mon 04.05.2015 | Partial Completion of implementation | Münevver, Sezgi, Hakan, <br /> Oğuz, Tuncay, Haluk, <br /> Morteza|
| Bug Fixing    | 56 days      | Mon 30.03.2015 | Mon 11.05.2015 | Partial Completion of implementation | Münevver, Sezgi, Hakan, <br /> Oğuz, Tuncay, Haluk, <br /> Morteza|
| User Acceptance Test (UAT) | 7 days       | Mon 04.05.2015 | Mon 11.05.2015 | Completion of Implementation | Münevver, Sezgi, Hakan, <br /> Oğuz, Tuncay, Haluk, <br /> Morteza|
| SPM version 1.0 | 7 days       | Mon 04.05.2015 | Mon 11.05.2015 | Completion of Implementation | Münevver, Sezgi, Hakan, <br /> Oğuz, Tuncay, Haluk, <br /> Morteza|