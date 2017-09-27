multibranchPipelineJob('forjj-modules') {
  description('Folder for Project forjj-modules generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          apiUri('https://api.github.com')
          includes('*')
          repoOwner('forj-oss')
          repository('forjj-modules')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
