\# Contributing Guide — Front-End Team



\*\*Project:\*\* E-Commerce React App

\*\*Last updated:\*\* 2026-10-07



By contributing to this project, you agree to follow every rule in this document.



\---



\## 1. Tech Stack (Non-Negotiable)



| Layer | Choice |

|---|---|

| Build tool | Vite |

| UI framework | React 18+ (function components only) |

| Styling | Tailwind CSS |

| UI components | shadcn/ui |

| Routing | React Router v6 |

| HTTP client | Axios |

| Forms | React Hook Form |

| Icons | lucide-react |

| State | React Context |



Installing a new library requires team approval first.



\---



\## 2. Code Style



Enforced by Prettier + ESLint. Run `npm run format` before committing.



\- Indentation: 2 spaces

\- Quotes: single

\- Semicolons: yes

\- Line length: 100 characters

\- Components: PascalCase (ProductCard.jsx)

\- Hooks/utils: camelCase (useAuth.js)

\- No console.log in commits



\---



\## 3. Styling Rules



Do:

\- Use Tailwind classes

\- Use theme tokens (bg-primary-600)

\- Use shared Button, Input, Card components

\- Responsive at 375px, 768px, 1280px



Don't:

\- Use inline style={{}}

\- Hardcode colors

\- Build your own Button or Input

\- Ship desktop-only layouts



\---



\## 4. Git Workflow



\### Branches

\- feature/short-description

\- fix/short-description



Never push to main or develop directly.



\### Commits

Format: type: short description



Types: feat, fix, style, refactor, docs, chore



Examples:

\- feat: add product list page

\- fix: cart total not updating

\- style: restyle navbar for mobile



\### Pushing

Run from the repo root, not inside frontend/:



&#x20; git add frontend/

&#x20; git commit -m "feat: ..."

&#x20; git push -u origin feature/your-task-name



Never run git add . or git add -A.



\---



\## 5. Pull Request Rules



Every PR must:

\- Target develop

\- Be reviewed by at least 1 other dev

\- Pass ESLint

\- Have no console.log

\- Include loading, error, and empty states

\- Be responsive



\---



\## 6. Definition of Done



A task is complete only when:

\- Works against real backend

\- No console errors

\- Loading state shown while fetching

\- Error state shown if request fails

\- Empty state shown if list is empty

\- Responsive at 375px, 768px, 1280px

\- No console.log

\- PR opened into develop

\- Reviewed and approved



\---



\## 7. Blocked or Unsure?



\- Missing API endpoint: ask backend owner

\- CORS error: ask backend owner

\- Auth not working: ask backend owner

\- Design decision: ask the other devs

\- Blocked more than 1 hour: post in chat immediately



Never guess. Never invent an endpoint. Never stay stuck alone.



\---



\## 8. Changing This Agreement



Raise it in team chat, discuss with all devs, then update this file and commit:



&#x20; git checkout develop

&#x20; git pull

&#x20; git checkout -b docs/update-contributing

&#x20; # edit CONTRIBUTING.md

&#x20; git add CONTRIBUTING.md

&#x20; git commit -m "docs: update contributing guide"

&#x20; git push -u origin docs/update-contributing



Then open a PR into develop.



Don't silently ignore a rule. Change it properly or follow it.



\---



\_Last updated: 2026-10-07\_

