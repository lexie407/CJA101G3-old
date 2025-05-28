**【團隊GitHub協作-每天開發流程】**

▼
[1] 開發前同步主線
└─► Pull… + 選master + Rebase
    即使沒寫程式，也要先rebase !!!

▼
[2] 切換到自己分支開始開發
└─► Team → Switch To 自己的分支

▼
[3] 若只是進度中
└─► 選擇 commit
    不需 push、不開 PR

▼
[4] 若功能完成
└─► commit + push
    再開 PR（選 main 為目標）

▼
[5] 組長負責 Review
└─► Approve 允許
    Request Changes退回

▼
[6] 奕欣負責 Merge PR

▼
[7] 若 PR 無法合併（conflict）
└─► PR 發起人需再次 rebase main
└─► 解決衝突後重新 push


